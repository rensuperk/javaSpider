package ren.superk.zhihu.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
import org.springframework.stereotype.Service;
import ren.superk.zhihu.core.ZhihuEnum;
import ren.superk.zhihu.model.People;
import ren.superk.zhihu.model.Relation;
import ren.superk.zhihu.model.ZhihuPager;
import ren.superk.zhihu.repository.PeopleRepository;
import ren.superk.zhihu.repository.RelationRepository;
import ren.superk.zhihu.service.PeopleService;
import ren.superk.zhihu.service.PeopleUrlService;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PeopleServiceImpl implements PeopleService {
    static Logger logger = LoggerFactory.getLogger(PeopleServiceImpl.class);

    public static ConcurrentHashMap<String,Relation> relationMap = new ConcurrentHashMap<>();
    static ArrayBlockingQueue<People> queryQueue = new ArrayBlockingQueue<People>(100, false);
    static AtomicInteger page = new AtomicInteger(0);

    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private RelationRepository relationRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private PeopleUrlService peopleUrlService;

    /**
     * 批量插入
     * @param list
     * @param type
     */
    @Override
    public void builkupsert(List<Map> list, ZhihuEnum type) {

        ArrayList<UpdateQuery> queries = new ArrayList<>();

        for (Map people : list) {
            try {
                if(people == null) continue;
                 queries.add(new UpdateQueryBuilder()
                         .withId(people.get(type.getPk()).toString())
                         .withType(type.getType())
                         .withIndexName(type.getIndex())
                         .withClass(Map.class).withDoUpsert(true)
                         .withIndexRequest(new IndexRequest()
                                            .source(people)
                                            .index(type.getIndex())
                                            .type(type.getType())
                                            .id(people.get(type.getPk()).toString()))
                         .build()
                 );
                 logger.debug("更新数据index = " +type.getIndex() + " type = " + type.getType() + " id = " + people.get(type.getPk()).toString());
            }catch (RuntimeException e){
                e.printStackTrace();
                logger.error(e.getLocalizedMessage());
            }
        }
        if (! queries.isEmpty())
        elasticsearchTemplate.bulkUpdate(queries);

    }

    /**
     * 存储爬到的当前位置
     * @param relation
     */
    public void builkupsertRelation(Relation relation) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String value = objectMapper.writeValueAsString(relation);
            ArrayList<UpdateQuery> queries = new ArrayList<>();
            UpdateQuery build = new UpdateQueryBuilder()
                    .withId(relation.getUrl_token())
                    .withType("relation")
                    .withIndexName("relation")
                    .withClass(Map.class).withDoUpsert(true)
                    .withIndexRequest(new IndexRequest()
                            .source(value, XContentType.JSON)
                            .index("relation")
                            .type("relation")
                            .id(relation.getUrl_token()))
                    .build();
            logger.info("更新数据index = relation, type = relation, id = " + relation.getUrl_token() + " ,  name = " + relation.getName() + " , count = " + relation.getFrom());
            elasticsearchTemplate.update(build);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }

    /**
     * 获取所有查询的位置
     * @return
     */
    @Override
    public ConcurrentHashMap<String, Relation> getAllRelations() {
        Iterator<Relation> iterator = relationRepository.findAll().iterator();
        while (iterator.hasNext()){
            Relation relation = iterator.next();
            relationMap.put(relation.getUrl_token(),relation);
            logger.info("已经处理的数据 url_token = " + relation.getUrl_token() + " name = " + relation.getName() + " count = " + relation.getFrom());
        }
        return relationMap;
    }

    /**
     * 初始方法
     * @param count
     */
    @Override
    public void initDataByThreadCount(int count) {
        //获取所有的记录
        getAllRelations();
        //开个线程池
        ExecutorService pool = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        //阻塞队列中数量小于10的时候就继续从库里面拿用户到队列中抓取
                        if(queryQueue.size() < 10){
                            setQueryQueue(page.incrementAndGet());
                        }
                        People poll = queryQueue.poll();
                        logger.info("开始处理数据url_token = " + poll.getUrl_token() + ",队列中剩余数量 = " + queryQueue.size());
                        //验证用户抓取到的位置1=已经抓取完了,0=开始未完成,-1=没有开始
                        int checkSaveCompleted = checkSaveCompleted(poll);
                        if(checkSaveCompleted != 1){
                            if(checkSaveCompleted == 0){
                                String type = null;
                                int from  = 0;
                                Relation relation = relationMap.get(poll.getUrl_token());
                                type = relation.getName();
                                from =relation.getFrom();
                                boolean running = false;

                                for (ZhihuEnum zhihuEnum : ZhihuEnum.values()) {
                                    //关注的人有时候太多了,为了先爬取别的,这个就先略过
                                    if(zhihuEnum == ZhihuEnum.FOLLOWERS){
                                        continue;
                                    }
                                    //只爬停顿之后的数据
                                    if(running){
                                        queryUrlAndSave(0,poll.getUrl_token(),zhihuEnum);
                                    }
                                    //从上次停顿的位置开始
                                    if(zhihuEnum.getValue().equals(type)){
                                        running = true;
                                        queryUrlAndSave(from,poll.getUrl_token(),zhihuEnum);
                                    }

                                }
                            }else{
                                for (ZhihuEnum zhihuEnum : ZhihuEnum.values()) {
                                    if(zhihuEnum == ZhihuEnum.FOLLOWERS){
                                        continue;
                                    }
                                    queryUrlAndSave(0,poll.getUrl_token(),zhihuEnum);
                                }
                            }


                        }
                    }

                }
            });
        }
    }

    /**
     * 爬取的主要逻辑
     * @param from
     * @param url_token
     * @param type
     */
    private void queryUrlAndSave(int from , String url_token,ZhihuEnum type){
        boolean isEnd = false;
        while (!isEnd){
            List<Map> listre = new ArrayList<>();
            try {
                //url方法
                ZhihuPager zhihuPager = peopleUrlService.findList(url_token, from, 20, type, ZhihuPager.class);
                //404返回这个用户的type就停止了,知乎返回停止也一样
                if(zhihuPager != null){
                    isEnd = (boolean) zhihuPager.getPaging().get("is_end");
                    listre = zhihuPager.getData();
                }else {
                    isEnd = true;
                }


            }catch (RuntimeException e){
                e.printStackTrace();
            }
            //这一段是因为知乎返回的数据不规整,需要手动处理
            List<Map> list = new ArrayList<>();
            for (Map o : listre) {
                Map map = new HashMap<>();
                if(type == ZhihuEnum.FOLLOWING_TOPIC_CONTRIBUTIONS){
                    map = (HashMap<Object, Object>) (o.get("topic"));
                }else if(type == ZhihuEnum.COLUMN_CONTRIBUTIONS){
                    map = (HashMap<Object, Object>) (o.get("columns"));
                }else {
                    map = o;
                }
                list.add(map);
            }
            builkupsert(list,type);
            //记录爬取的问题
            if(!list.isEmpty())
                saveRelation(url_token,type,from);
            //爬取下一页
            from +=20;
        }




    }
    //存储爬取记录
    private void saveRelation(String url_token, ZhihuEnum type, int count){
        Relation relation = new Relation();
        relation.setUrl_token(url_token);
        relation.setFrom(count);
        relation.setName(type.getValue());

        builkupsertRelation(relation);
        relationMap.put(url_token,relation);

    }

    /**
     * 检查示范完成
     * @param people
     * @return
     */
    private int checkSaveCompleted(People people){
        Relation relation = relationMap.get(people.getUrl_token());
            if(relation != null){
                //当用户的被关注人爬取完成的时候我们认为爬取完成了
                if(ZhihuEnum.FOLLOWERS.getValue().equals(relation.getName()) && people.getFollower_count().equals(relation.getFrom())){
                        return  1;
                }else {
                    return 0;
                }
            }
        return -1;
    }
    /**
     * 设置到处理队列
     * @param page
     */
    private void setQueryQueue(int page){
        //抓取10条记录到任务队列
        Page<People> list = peopleRepository.findAll(PageRequest.of(page, 10, Sort.by("follower_count").descending()));
        Iterator<People> peopleIterator = list.iterator();
        while (peopleIterator.hasNext()){
            People next = peopleIterator.next();
            String url_token = next.getUrl_token();
            boolean offer = queryQueue.offer(next);
            if(offer){
                logger.info("url_token : " + url_token + " 加入处理队列");
            }
        }

    }
}

package ren.superk.zhihu.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
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
import ren.superk.zhihu.model.ZhihuPeoplePager;
import ren.superk.zhihu.repository.PeopleRepository;
import ren.superk.zhihu.repository.RelationRepository;
import ren.superk.zhihu.service.PeopleService;
import ren.superk.zhihu.service.PeopleUrlService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@Service
public class PeopleServiceImpl implements PeopleService {
    public static ConcurrentHashMap<String,Relation> relationMap = new ConcurrentHashMap<>();
    static ArrayBlockingQueue<People> queryQueue = new ArrayBlockingQueue<People>(10, false);
    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private RelationRepository relationRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private PeopleUrlService peopleUrlService;

    @Override
    public<T> void builkupsert(List<T> list) {
        ArrayList<UpdateQuery> queries = new ArrayList<>();
        for (T people : list) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String value = objectMapper.writeValueAsString(people);
                String index = null;
                String type = null;
                String id = null;
                if (people instanceof People){
                    index = "zhihu";
                    type ="people";
                    id =  ((People)people).getUrl_token();
                }else if(people instanceof Relation){
                    index = "relation";
                    type ="relation";
                    id = ((Relation)people).getUrl_token();
                }else {
                    throw new RuntimeException();
                }
                 queries.add(new UpdateQueryBuilder()
                         .withId(id)
                         .withType(type)
                         .withIndexName(index)
                         .withClass(People.class).withDoUpsert(true)
                         .withIndexRequest(new IndexRequest()
                                            .source(value, XContentType.JSON)
                                            .index(index)
                                            .type(type)
                                            .id(id))
                         .build()
                 );
            }catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if (!queries.isEmpty())
        elasticsearchTemplate.bulkUpdate(queries);

    }

    @Override
    public ConcurrentHashMap<String, Relation> getAllRelations() {
        Iterator<Relation> iterator = relationRepository.findAll().iterator();
        while (iterator.hasNext()){
            Relation relation = iterator.next();
            relationMap.put(relation.getUrl_token(),relation);
        }
        return relationMap;
    }

    @Override
    public void initDataByThreadCount(int count) {
        ConcurrentHashMap<String, Relation> relations = getAllRelations();
        Random random = new Random();
        setQueryQueue(random.nextInt(10));
        ExecutorService pool = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        People poll = queryQueue.poll();
                        if(!checkSaveCompleted(poll,ZhihuEnum.FOLLOWEES)){
                            queryUrlAndSave(0,poll.getUrl_token(),ZhihuEnum.FOLLOWEES,null);
                        }
                        if(!checkSaveCompleted(poll,ZhihuEnum.FOLLOWERS)){
                            queryUrlAndSave(0,poll.getUrl_token(),ZhihuEnum.FOLLOWERS,null);
                        }
                    }

                }
            });
        }
    }

    private void queryUrlAndSave(int from , String url_token,ZhihuEnum type,List<String> url_tokens){
        List<People> list = new ArrayList<>();
        try {
             list = peopleUrlService.findList(url_token, from, 20, type, ZhihuPeoplePager.class).getData();

        }catch (RuntimeException e){
            e.printStackTrace();
        }
        builkupsert(list);
        if(url_tokens == null){
            url_tokens = new ArrayList<>();
        }

        List<String> newqreryList = new ArrayList<>();
        for (People people : list) {
            url_tokens.add(people.getUrl_token());
            newqreryList.add(people.getUrl_token());
            if(relationMap.contains(url_token)){
                continue;
            }else {
                queryQueue.offer(people);
            }
        }
        saveRelation(url_token,type,newqreryList);
        if(list.size() == 20){
            queryUrlAndSave(from + 20,url_token,type,url_tokens);
        }

    }
    private void saveRelation(String url_token,ZhihuEnum type,List<String> url_tokens){
        Relation relation = relationMap.get(url_token);
        Integer Eecur = 0;
        Integer Ercur = 0;
        if(relation == null){
            relation = new Relation();
            relation.setUrl_token(url_token);
        }else {
            Eecur = relation.getEecur();
            Ercur = relation.getErcur();
        }
        if(type == ZhihuEnum.FOLLOWEES){
            relation.setEecur(Eecur + url_tokens.size());
            relation.getFollowees().addAll(url_tokens);
        }else if(type == ZhihuEnum.FOLLOWERS){
            relation.setErcur(Ercur + url_tokens.size());
            relation.getFollowers().addAll(url_tokens);
        }

        ArrayList<Relation> relations = new ArrayList<>();
        relations.add(relation);
        builkupsert(relations);
        relationMap.put(url_token,relation);

    }
    private boolean checkSaveCompleted(People people, ZhihuEnum type){
        Relation relation = relationMap.get(people.getUrl_token());
        if(ZhihuEnum.FOLLOWEES == type){
            if(relation != null){
                return relation.getFollowees().size() == people.getFollowing_count();
            }
        }else if(ZhihuEnum.FOLLOWERS == type){
            if(relation != null){
                return relation.getFollowers().size() == people.getFollowing_count();
            }
        }
        return false;
    }
    /**
     * 设置到处理队列
     * @param page
     */
    private void setQueryQueue(int page){
        Page<People> list = peopleRepository.findAll(PageRequest.of(page, 20, Sort.by("follower_count").descending()));
        Iterator<People> peopleIterator = list.iterator();
        while (peopleIterator.hasNext()){
            People next = peopleIterator.next();
            String url_token = next.getUrl_token();
            if(!relationMap.contains(url_token)) {
                queryQueue.offer(next);
                System.out.println("url_token : " + url_token + " 加入处理队列=====================");
            }else {
                System.out.println("url_token : " + url_token + " 已经处理过,略过=====================");
            }
        }
        if(queryQueue.size() < 6){
            setQueryQueue(page+1);
        }
    }
}

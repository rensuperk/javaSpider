package ren.superk.zhihu;

import groovy.json.JsonSlurper;
import io.netty.util.internal.ConcurrentSet;
import org.apache.logging.log4j.LogManager;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


public class SpiderMain4 {
    private final static org.apache.logging.log4j.Logger log = LogManager.getLogger(SpiderMain4.class);
    static ArrayBlockingQueue<Map> queryQueue = new ArrayBlockingQueue<Map>(10, false);
    private static ConcurrentSet<String> hasStoreSet = new ConcurrentSet<String>();


    public static void main(String[] args) {
        Executor executorService = Executors.newCachedThreadPool();
        final SpiderMain4 spiderMain = new SpiderMain4();
        Random random = new Random();
        //将所有的已经查询的relation存入set中
        spiderMain.setRelations(0);
        //查询存入队列中
        spiderMain.setAllPeople(random.nextInt(10000));

        for (int i = 0; i < 2; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                while (true) {
                    SpiderMain4 spiderMain = new SpiderMain4();
                    Map people =queryQueue.poll() ;
                    String url_token;
                    url_token= (String) people.get("url_token") ;
                    System.out.println("开始查询用户url_token = " +url_token + "的信息关注者和被关注者,队列中待查对象有 : ");
                    for (Map map : queryQueue) {
                        System.out.println(map.get("url_token"));
                    }
                    List<Map> getfollowing = spiderMain.getfollowing(url_token);
                    List<Map> getfollower =  new ArrayList<Map>();
//                    getfollower = spiderMain.getfollower(url_token);
                    if(getfollowing.isEmpty() && getfollower.isEmpty()){
                        continue;
                    }
                    spiderMain.saveRelation(url_token,getfollowing,getfollower);
                    hasStoreSet.add(url_token);
                        for (Map map : getfollowing) {
                            if(!hasStoreSet.contains((String) map.get("url_token"))){
                                queryQueue.offer(map);
                            }else {
                                System.out.println("url_token = " + map.get("url_token") + " 已经处理过,略过=====================");
                            }
                        }
                }
                }

            });
        }
    }
    public Map getPeople(String user_token){
        //id索引
        //子线程的异常没办法抛出被主线程捕获，必须要在子线程捕获
        URL url = null;
        try {
            url = new URL("https://www.zhihu.com/api/v4/members/" + user_token + "?include=locations,employments,gender,educations,business,voteup_count,thanked_Count,follower_count,following_count,cover_url,following_topic_count,following_question_count,following_favlists_count,following_columns_count,avatar_hue,answer_count,articles_count,pins_count,question_count,columns_count,commercial_question_count,favorite_count,favorited_count,logs_count,marked_answers_count,marked_answers_text,message_thread_token,account_status,is_active,is_bind_phone,is_force_renamed,is_bind_sina,is_privacy_protected,sina_weibo_url,sina_weibo_name,show_sina_weibo,is_blocking,is_blocked,is_following,is_followed,mutual_followees_count,vote_to_count,vote_from_count,thank_to_count,thank_from_count,thanked_count,description,hosted_live_count,participated_live_count,allow_message,industry_category,org_name,org_homepage,badge[?(type=best_answerer)].topics");

        HttpURLConnection conn = null;

            conn = (HttpURLConnection) url.openConnection();


            conn.setRequestMethod("GET");

        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(true);
        conn.setInstanceFollowRedirects(true);
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0");
        conn.setRequestProperty("authorization", "oauth c3cef7c66a1843f8b3a9e6a1e3160e20");
        conn.setRequestProperty("Cookie", "q_c1=6456e84e406c471fb651d1c6d16f3658|1504513188000|1504513188000; r_cap_id=\"MmY2YmRjZmU5YjM0NGY5MzkxMGZkZWI4M2NmNTFiYTM=|1504592096|19d1a6da790b828aee5bb1dbd6a73dc4848f4ac0\"; cap_id=\"M2VhMWI3NjNlNGI1NGU5NmE0YmU0NTE5NDdkNGY3NmI=|1504592096|461e3951a20090ab01f9faeede84159bb0767a2d\"; l_cap_id=\"NWYxOGM0MGEyODAyNDkzNjhhOTE0Yzc2NTI5ODgwZTY=|1504592096|64b4848d40e458378f7fb3a048a3cbea758b1ef5\"; _zap=03fd7012-a82f-4326-a31a-fda9edeea35a; __utma=51854390.1332223617.1504592571.1504592571.1504592571.1; __utmz=51854390.1504592571.1.1.utmcsr=zhihu.com|utmccn=(referral)|utmcmd=referral|utmcct=/people/ji-heng-chao-15/following; l_n_c=1; _xsrf=8884af3c51308f3aa78bc8494db2f387; n_c=1; _xsrf=8884af3c51308f3aa78bc8494db2f387; aliyungf_tc=AQAAAD0ivzAIcAoApoV3AegnZKar8ND3; d_c0=\"AZDCBdNAVAyPThAQUw5YqYnJqItjrISacw4=|1504592568\"; __utmb=51854390.0.10.1504592571; __utmc=51854390; __utmv=51854390.000--|3=entry_date=20170904=1/T; _xsrf=ff431df1-b416-4b41-81d4-9c639bb53b41");
        conn.setRequestProperty("Accept-Charset", "UTF-8");
        conn.setRequestProperty("Cache-Control", "max-age=0");
        conn.setRequestProperty("x-udid", "AZDCBdNAVAyPThAQUw5YqYnJqItjrISacw4=");
        conn.setRequestProperty("contentType", "UTF-8");

            conn.connect();

        JsonSlurper jsonSlurper = new JsonSlurper();

            return (Map) jsonSlurper.parse(conn.getInputStream(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }//获取主话题
    private List<Map> getfollowing(String url_token) {
        return getfollowing(url_token,0,null,"followees");
    }

    private List<Map> getfollower(String url_token) {
        return getfollowing(url_token,0,null,"followers");
    }

    public void setAllPeople(int offset) {
        //查询没有处理过的加入队列中去等待处理
        TransportClient client = setclient();
        SearchResponse response = client
                .prepareSearch("zhihu")
                .setTypes("people")
                .setFrom(offset)
                .setSize(100)
                .addSort("follower_count", SortOrder.DESC)

                .get();
        for (SearchHit searchHitFields : response.getHits().getHits()) {
            if(!hasStoreSet.contains(searchHitFields.getId())) {
                queryQueue.offer(searchHitFields.getSource());
            }else {
                System.out.println("url_token" + searchHitFields.getId() + " 已经处理过,略过=====================");
            }
            System.out.println(Thread.currentThread().getName()+": 添加处理队列"+searchHitFields.getId());
        }
        client.close();
        if (queryQueue.isEmpty()){
            setAllPeople(offset + 100);
        }
    }

    public Map getPeopleFromes(String url_token) {
        TransportClient client = setclient();
        GetResponse fields = client.prepareGet("zhihu", "people", url_token).get();
        Map<String, Object> source = fields.getSource();
        client.close();
        return source;
    }
    public  TransportClient setclient() {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("192.168.56.101", 9300)));
        return client;
    }

    public void savePeople(List<Map> getfollowing) {
        TransportClient client = setclient();
        try {
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            for (Map people : getfollowing) {
                try {
                    String url_token = people.get("url_token").toString();
                    IndexRequest indexRequest = new IndexRequest("zhihu", "people", url_token)
                            .source(jsonBuilder()
                                    .map(people));
                    UpdateRequest updateRequest = new UpdateRequest("zhihu", "people",url_token)
                            .doc(jsonBuilder()
                                    .map(people));
                    updateRequest.upsert(indexRequest);
                    bulkRequest.add(updateRequest);
                    System.out.println(Thread.currentThread().getName() + "更新数据url_token = " + url_token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (bulkRequest.numberOfActions() > 0) {
                BulkResponse bulkItemResponses = bulkRequest.get();
                System.out.println(Thread.currentThread().getName() + ":共有数据"+bulkItemResponses.getItems().length+" 条,数据插入 "  + bulkRequest.numberOfActions());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
    public void saveRelation(String url_token,List<Map> getfollowing,List<Map> follower) {
        TransportClient client = setclient();
        try {
            Map<String, Object> relation = new HashMap<String, Object>();
            relation.put("url_token",url_token);
            ArrayList<String> fee = new ArrayList<String>();
            ArrayList<String> fer = new ArrayList<String>();
            for (Map people : getfollowing) {
                fee.add(people.get("url_token").toString());
            }
            relation.put("following",fee);
            for (Map people : follower) {
                fer.add(people.get("url_token").toString());
            }
            relation.put("follower",fer);
            IndexRequest indexRequest = new IndexRequest("relation", "relation", url_token)
                    .source(jsonBuilder()
                            .map(relation));
            UpdateRequest updateRequest = new UpdateRequest("relation", "relation",url_token)
                    .doc(jsonBuilder()
                            .map(relation));
            updateRequest.upsert(indexRequest);
            client.update(updateRequest).get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
    public void setRelations(int from) {
        TransportClient client = setclient();
        SearchResponse response = null;
        try {
            response = client.prepareSearch("relation").setTypes("relation").setFrom(from).setSize(30000).get();
            for (SearchHit fields : response.getHits().getHits()) {
                hasStoreSet.add(fields.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
        if(response.getHits().getHits().length == 30000L){
            setRelations(from + 30000);
        }
    }
    public void copy(){
            TransportClient client = setclient();
            SearchResponse response = null;
            try {
                response = client.prepareSearch("zhihu").setTypes("relation").setFrom(0).setSize(30000).get();
                for (SearchHit fields : response.getHits().getHits()) {
                    IndexRequest indexRequest = new IndexRequest("relation", "relation", fields.getId())
                            .source(jsonBuilder()
                                    .map(fields.getSource()));
                    UpdateRequest updateRequest = new UpdateRequest("relation", "relation",fields.getId())
                            .doc(jsonBuilder()
                                    .map(fields.getSource()));
                    updateRequest.upsert(indexRequest);
                    client.update(updateRequest).get();
                    client.prepareDelete().setIndex("zhihu").setType("relation").setId(fields.getId()).get();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                client.close();
            }

        }


    public List<Map> getfollowing(String user_token, Integer offset, List<Map> following, String followers) {
        if (following == null) {
            following = new ArrayList<Map>();
        }
        //id索引
        try {
//            System.out.println(Thread.currentThread().getName()+user_token+" : 开始查询"+offset);
            URL url = new URL("https://www.zhihu.com/api/v4/members/" + user_token + "/"+followers+"?offset=" + offset + "&limit=20&include=data[*].locations,employments,gender,educations,business,voteup_count,thanked_Count,follower_count,following_count,cover_url,following_topic_count,following_question_count,following_favlists_count,following_columns_count,avatar_hue,answer_count,articles_count,pins_count,question_count,columns_count,commercial_question_count,favorite_count,favorited_count,logs_count,marked_answers_count,marked_answers_text,message_thread_token,account_status,is_active,is_bind_phone,is_force_renamed,is_bind_sina,is_privacy_protected,sina_weibo_url,sina_weibo_name,show_sina_weibo,is_blocking,is_blocked,is_following,is_followed,mutual_followees_count,vote_to_count,vote_from_count,thank_to_count,thank_from_count,thanked_count,description,hosted_live_count,participated_live_count,allow_message,industry_category,org_name,org_homepage,badge[?(type=best_answerer)].topics");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(true);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0");
            conn.setRequestProperty("authorization", "oauth c3cef7c66a1843f8b3a9e6a1e3160e20");
            conn.setRequestProperty("Cookie", "q_c1=6456e84e406c471fb651d1c6d16f3658|1504513188000|1504513188000; r_cap_id=\"NjIxYjI5YmUxYzU3NDUwNGJkOWQwYWU0YmViNzU4ODQ=|1504747681|dd14796594f61fcc9040110699c63705a9de3e2a\"; cap_id=\"ZmEyY2I1YWViNDM1NGU3ODk0OTk1NmQyNDlmZGFjOGY=|1504747681|9d1069b84ad5bdb14f2d62a147c147e76d8d9451\"; l_cap_id=\"OWRiZjY2NDMzNjNmNGRhNWE3OGJjYjg4ZThmM2NiNjg=|1504747681|5bbe2e7a124454afdc0e73c06221c4c8e423ff72\"; _zap=03fd7012-a82f-4326-a31a-fda9edeea35a; __utma=51854390.1332223617.1504592571.1504592571.1504592571.1; __utmz=51854390.1504592571.1.1.utmcsr=zhihu.com|utmccn=(referral)|utmcmd=referral|utmcct=/people/ji-heng-chao-15/following; d_c0=\"AZDCBdNAVAyPThAQUw5YqYnJqItjrISacw4=|1504592568\"; __utmv=51854390.000--|3=entry_date=20170904=1; _xsrf=b4e37fae-2674-43ca-bb2a-3c59f0dbd033");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Cache-Control", "max-age=0");
            conn.setRequestProperty("x-udid", "AZDCBdNAVAyPThAQUw5YqYnJqItjrISacw4=");
            conn.setRequestProperty("contentType", "UTF-8");
            conn.connect();
            JsonSlurper jsonSlurper = new JsonSlurper();
            Map parse = (Map<String, Object>) jsonSlurper.parse(conn.getInputStream(), "UTF-8");
            List<Map> data = (List<Map>) parse.get("data");
            if (data != null && !data.isEmpty()) {
                following.addAll(data);
                savePeople(data);
                if (data.size() == 20) {
                    getfollowing(user_token, offset + 20, following,followers);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return following;
    }

}
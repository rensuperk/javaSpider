package ren.superk.zhihu;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import groovy.json.JsonSlurper;
import io.netty.util.internal.ConcurrentSet;
import org.apache.logging.log4j.LogManager;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.existsQuery;


public class SpiderMain3 {
    private final static org.apache.logging.log4j.Logger log = LogManager.getLogger(SpiderMain3.class);
    static ArrayBlockingQueue<Map> queryQueue = new ArrayBlockingQueue<Map>(100, false);
    private static ConcurrentSet<String> stringConcurrentSet = new ConcurrentSet<String>();


    public static void main(String[] args) {
        Executor executorService = Executors.newCachedThreadPool();
        final SpiderMain3 spiderMain = new SpiderMain3();
        Random random = new Random();
        spiderMain.setAllPeople(random.nextInt(10000));

        for (int i = 0; i < 4; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                while (true) {
                    SpiderMain3 spiderMain = new SpiderMain3();
                    Map people =queryQueue.poll() ;
                    String url_token;
                    url_token= (String) people.get("url_token") ;
                        List<Map> getfollowing = spiderMain.getfollower(url_token);
                        for (Map map : getfollowing) {
                            queryQueue.offer(map);
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
                .setSize(99)
                .addSort("following_count", SortOrder.DESC)
                .setQuery(QueryBuilders.boolQuery()
                        .mustNot(existsQuery("followee")))
                .get();
        for (SearchHit searchHitFields : response.getHits().getHits()) {
            queryQueue.offer(searchHitFields.getSource());
            System.out.println(Thread.currentThread().getName()+": 添加处理队列"+searchHitFields.getId());
        }
        client.close();

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
            int insert = 0;
            int update = 0;
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (bulkRequest.numberOfActions() > 0) {
                BulkResponse bulkItemResponses = bulkRequest.get();
                System.out.println(Thread.currentThread().getName() + ":共有数据"+bulkItemResponses.getItems().length+" 条,数据插入 "  + insert + ",数据更新 " + (update));
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
            System.out.println(Thread.currentThread().getName()+user_token+" : 开始查询"+offset);
            URL url = new URL("https://www.zhihu.com/api/v4/members/" + user_token + "/"+followers+"?offset=" + offset + "&limit=20&include=locations,employments,gender,educations,business,voteup_count,thanked_Count,follower_count,following_count,cover_url,following_topic_count,following_question_count,following_favlists_count,following_columns_count,avatar_hue,answer_count,articles_count,pins_count,question_count,columns_count,commercial_question_count,favorite_count,favorited_count,logs_count,marked_answers_count,marked_answers_text,message_thread_token,account_status,is_active,is_bind_phone,is_force_renamed,is_bind_sina,is_privacy_protected,sina_weibo_url,sina_weibo_name,show_sina_weibo,is_blocking,is_blocked,is_following,is_followed,mutual_followees_count,vote_to_count,vote_from_count,thank_to_count,thank_from_count,thanked_count,description,hosted_live_count,participated_live_count,allow_message,industry_category,org_name,org_homepage,badge[?(type=best_answerer)].topics");
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
            conn.setRequestProperty("Cookie", "q_c1=6456e84e406c471fb651d1c6d16f3658|1504513188000|1504513188000; r_cap_id=\"MmY2YmRjZmU5YjM0NGY5MzkxMGZkZWI4M2NmNTFiYTM=|1504592096|19d1a6da790b828aee5bb1dbd6a73dc4848f4ac0\"; cap_id=\"M2VhMWI3NjNlNGI1NGU5NmE0YmU0NTE5NDdkNGY3NmI=|1504592096|461e3951a20090ab01f9faeede84159bb0767a2d\"; l_cap_id=\"NWYxOGM0MGEyODAyNDkzNjhhOTE0Yzc2NTI5ODgwZTY=|1504592096|64b4848d40e458378f7fb3a048a3cbea758b1ef5\"; _zap=03fd7012-a82f-4326-a31a-fda9edeea35a; __utma=51854390.1332223617.1504592571.1504592571.1504592571.1; __utmz=51854390.1504592571.1.1.utmcsr=zhihu.com|utmccn=(referral)|utmcmd=referral|utmcct=/people/ji-heng-chao-15/following; l_n_c=1; _xsrf=8884af3c51308f3aa78bc8494db2f387; n_c=1; _xsrf=8884af3c51308f3aa78bc8494db2f387; aliyungf_tc=AQAAAD0ivzAIcAoApoV3AegnZKar8ND3; d_c0=\"AZDCBdNAVAyPThAQUw5YqYnJqItjrISacw4=|1504592568\"; __utmb=51854390.0.10.1504592571; __utmc=51854390; __utmv=51854390.000--|3=entry_date=20170904=1/T; _xsrf=ff431df1-b416-4b41-81d4-9c639bb53b41");
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
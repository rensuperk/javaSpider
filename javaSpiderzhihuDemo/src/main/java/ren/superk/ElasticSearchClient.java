package ren.superk;


import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Date;


public class ElasticSearchClient {
    private  TransportClient client;
    public static void main(String[] args) throws IOException {
        ElasticSearchClient elasticSearchClient = new ElasticSearchClient();
        TransportClient client = elasticSearchClient.setclient();
        // on startup
//        IndexResponse response = client.prepareIndex("twitter", "tweet","1")
//                .setSource(jsonBuilder()
//                        .startObject()
//                        .field("user", "kimchy")
//                        .field("postDate", new Date())
//                        .field("message", "trying out Elasticsearch")
//                        .endObject()
//                )
//                .get();
        GetResponse response = client.prepareGet("twitter", "tweet", "1").get();
        GetResponse response2 = client.prepareGet("twitter", "tweet", "1")
                .setOperationThreaded(false)
                .get();
        System.out.println(response.toString());
        System.out.println(response2.toString());
        client.close();
    }
    public   TransportClient setclient(){
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
//                .put("client.transport.sniff", true)
                .build();
        this.client = new PreBuiltTransportClient(settings)
                //                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192."), 9300))
                .addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress("192.168.13.34",9300)));
        return client;
    }

}



//package ren.superk.zhihu.config;
//
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "ren.superk")
//public class ElasticSearchConfig implements FactoryBean<TransportClient>, InitializingBean, DisposableBean {
//    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);
//
//    @Value("${spring.data.elasticsearch.cluster-nodes}")
//    private String clusterNodes;
//
//    private TransportClient transportClient;
//    private PreBuiltTransportClient preBuiltTransportClient;
//
//    @Override
//    public void destroy() throws Exception {
//        try {
//            logger.info("Closing elasticSearch client");
//            if (transportClient != null) {
//                transportClient.close();
//            }
//        } catch (final Exception e) {
//            logger.error("Error closing ElasticSearch client: ", e);
//        }
//    }
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
//        return new ElasticsearchTemplate(buildClient());
//    }
//    @Override
//    public TransportClient getObject() throws Exception {
//        return transportClient;
//    }
//
//    @Override
//    public Class<TransportClient> getObjectType() {
//        return TransportClient.class;
//    }
//
//    @Override
//    public boolean isSingleton() {
//        return false;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        buildClient();
//    }
//
//    protected TransportClient buildClient() {
//        try {
//            preBuiltTransportClient = new PreBuiltTransportClient(settings());
//
//            String InetSocket[] = clusterNodes.split(":");
//            String address = InetSocket[0];
//            Integer port = Integer.valueOf(InetSocket[1]);
//            transportClient = preBuiltTransportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(address), port));
//
//        } catch (UnknownHostException e) {
//            logger.error(e.getMessage());
//        }
//        return transportClient;
//    }
//
//    /**
//     * 初始化默认的client
//     */
//    private Settings settings() {
////		Settings settings = Settings.builder().put("cluster.name", clusterName).put("client.transport.sniff", true).build();
//        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
//        return settings;
//    }
//}
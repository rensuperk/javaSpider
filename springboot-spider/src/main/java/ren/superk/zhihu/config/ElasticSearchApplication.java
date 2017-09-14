package ren.superk.zhihu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import ren.superk.zhihu.service.PeopleService;


@SpringBootApplication(scanBasePackages = "ren.superk")
@EnableElasticsearchRepositories(basePackages = "ren.superk")
public class ElasticSearchApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchApplication.class,args);
    }


    @Autowired
    private PeopleService peopleService;
    @Override
    public void run(String... strings) throws Exception {
        peopleService.initDataByThreadCount(2);
    }
}

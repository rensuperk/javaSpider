package ren.superk.zhihu.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ren.superk.zhihu.model.People;

public interface PeopleRepository extends ElasticsearchRepository<People,String> {
}

package ren.superk.zhihu.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ren.superk.zhihu.model.People;
import ren.superk.zhihu.model.Relation;

public interface RelationRepository extends ElasticsearchRepository<Relation,String> {
}

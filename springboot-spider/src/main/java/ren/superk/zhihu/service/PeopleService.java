package ren.superk.zhihu.service;

import ren.superk.zhihu.core.ZhihuEnum;
import ren.superk.zhihu.model.Relation;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface PeopleService {

    void builkupsert(List<Map> list, ZhihuEnum type);



    ConcurrentHashMap<String, Relation> getAllRelations();

    void initDataByThreadCount(int count);
}

package ren.superk.zhihu.service;

import ren.superk.zhihu.model.Relation;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface PeopleService {

    <T> void builkupsert(List<T> list);


    ConcurrentHashMap<String, Relation> getAllRelations();

    void initDataByThreadCount(int count);
}

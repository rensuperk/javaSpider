package ren.superk.zhihu.service;

import ren.superk.zhihu.core.ZhihuEnum;
import ren.superk.zhihu.model.People;

public interface PeopleUrlService {

    <T> T findList(String url_token, Integer from, Integer limit, ZhihuEnum followees, Class<T> t);

    People getPeople(String id);
}

package ren.superk.zhihu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZhihuPager<T> {
    List<T> data = new ArrayList<T>();
    Map paging;

    public<T> ZhihuPager() {
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Map getPaging() {
        return paging;
    }

    public void setPaging(Map paging) {
        this.paging = paging;
    }
}

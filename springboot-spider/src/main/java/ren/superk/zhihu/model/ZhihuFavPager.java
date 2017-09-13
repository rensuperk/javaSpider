package ren.superk.zhihu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZhihuFavPager {
    List<Fav> data = new ArrayList<Fav>();
    Map pagging;

    public List<Fav> getData() {
        return data;
    }

    public void setData(List<Fav> data) {
        this.data = data;
    }

    public Map getPagging() {
        return pagging;
    }

    public void setPagging(Map pagging) {
        this.pagging = pagging;
    }
}

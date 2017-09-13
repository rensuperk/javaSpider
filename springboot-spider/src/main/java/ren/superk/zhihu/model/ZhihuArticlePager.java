package ren.superk.zhihu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZhihuArticlePager {
    List<Article> data = new ArrayList<Article>();
    Map pagging;

    public List<Article> getData() {
        return data;
    }

    public void setData(List<Article> data) {
        this.data = data;
    }

    public Map getPagging() {
        return pagging;
    }

    public void setPagging(Map pagging) {
        this.pagging = pagging;
    }
}

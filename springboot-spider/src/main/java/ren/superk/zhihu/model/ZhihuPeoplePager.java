package ren.superk.zhihu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZhihuPeoplePager {
    List<People> data = new ArrayList<People>();
    Map pagging;

    public List<People> getData() {
        return data;
    }

    public void setData(List<People> data) {
        this.data = data;
    }

    public Map getPagging() {
        return pagging;
    }

    public void setPagging(Map pagging) {
        this.pagging = pagging;
    }
}

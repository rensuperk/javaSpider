package ren.superk.zhihu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZhihuColumnsPager {
    List<Columns> data = new ArrayList<Columns>();
    Map pagging;

    public List<Columns> getData() {
        return data;
    }

    public void setData(List<Columns> data) {
        this.data = data;
    }

    public Map getPagging() {
        return pagging;
    }

    public void setPagging(Map pagging) {
        this.pagging = pagging;
    }
}

package ren.superk.zhihu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZhihuPinsPager {
    List<Pins> data = new ArrayList<Pins>();
    Map pagging;

    public List<Pins> getData() {
        return data;
    }

    public void setData(List<Pins> data) {
        this.data = data;
    }

    public Map getPagging() {
        return pagging;
    }

    public void setPagging(Map pagging) {
        this.pagging = pagging;
    }
}

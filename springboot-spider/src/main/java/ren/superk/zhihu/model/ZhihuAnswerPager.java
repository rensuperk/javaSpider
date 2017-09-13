package ren.superk.zhihu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZhihuAnswerPager {
    List<Answer> data = new ArrayList<Answer>();
    Map pagging;

    public List<Answer> getData() {
        return data;
    }

    public void setData(List<Answer> data) {
        this.data = data;
    }

    public Map getPagging() {
        return pagging;
    }

    public void setPagging(Map pagging) {
        this.pagging = pagging;
    }
}

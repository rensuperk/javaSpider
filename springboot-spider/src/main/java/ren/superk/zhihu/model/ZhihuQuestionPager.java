package ren.superk.zhihu.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZhihuQuestionPager {
    List<Question> data = new ArrayList<Question>();
    Map pagging;

    public List<Question> getData() {
        return data;
    }

    public void setData(List<Question> data) {
        this.data = data;
    }

    public Map getPagging() {
        return pagging;
    }

    public void setPagging(Map pagging) {
        this.pagging = pagging;
    }
}

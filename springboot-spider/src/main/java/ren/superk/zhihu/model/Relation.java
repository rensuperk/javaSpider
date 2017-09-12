package ren.superk.zhihu.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;

@Document(indexName = "relation", type = "relation")
public class Relation {
    @Id
    private String url_token;
    //当前处理的数量
    private int eecur;
    private int ercur;
    private ArrayList<String> followees = new ArrayList<>();
    private ArrayList<String> followers = new ArrayList<>();

    public Integer getEecur() {
        return eecur;
    }

    public void setEecur(Integer eecur) {
        this.eecur = eecur;
    }

    public Integer getErcur() {
        return ercur;
    }

    public void setErcur(Integer ercur) {
        this.ercur = ercur;
    }

    public String getUrl_token() {
        return url_token;
    }

    public void setUrl_token(String url_token) {
        this.url_token = url_token;
    }

    public ArrayList<String> getFollowees() {
        return followees;
    }

    public void setFollowees(ArrayList<String> followees) {
        this.followees = followees;
    }

    public ArrayList<String> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<String> followers) {
        this.followers = followers;
    }
}

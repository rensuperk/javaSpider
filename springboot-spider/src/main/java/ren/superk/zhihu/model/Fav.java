package ren.superk.zhihu.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "fav", type = "v1")
public class Fav {
    private Long id;
    private Long updated_time;
    private Integer answer_count;
    private Integer follower_count;
    private Boolean is_public;
    private String title;
    private String type;
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(Long updated_time) {
        this.updated_time = updated_time;
    }

    public Integer getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(Integer answer_count) {
        this.answer_count = answer_count;
    }

    public Integer getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(Integer follower_count) {
        this.follower_count = follower_count;
    }

    public Boolean getIs_public() {
        return is_public;
    }

    public void setIs_public(Boolean is_public) {
        this.is_public = is_public;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

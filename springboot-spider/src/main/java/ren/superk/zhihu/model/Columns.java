package ren.superk.zhihu.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "columns", type = "v1")
public class Columns {
    private  Integer articles_count;
    private  Integer followers;
    private People  author;
    private String comment_permission;
    private String id;
    private String image_url;
    private String intro;
    private String title;
    private String type;
    private String updated;
    private String url;


    public Integer getArticles_count() {
        return articles_count;
    }

    public void setArticles_count(Integer articles_count) {
        this.articles_count = articles_count;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public People getAuthor() {
        return author;
    }

    public void setAuthor(People author) {
        this.author = author;
    }

    public String getComment_permission() {
        return comment_permission;
    }

    public void setComment_permission(String comment_permission) {
        this.comment_permission = comment_permission;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

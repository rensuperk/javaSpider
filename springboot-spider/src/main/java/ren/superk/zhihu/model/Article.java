package ren.superk.zhihu.model;

import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;
import java.util.Map;
@Document(indexName = "article", type = "v1")
public class Article {
    private Boolean admin_closed_comment;
    private People author;
    private Integer comment_count;
    private String comment_permission;
    private String voteup_count;
    private String voting;
    private String content;
    private String excerpt;
    private String excerpt_title;
    private String image_url;
    private String title;
    private String type;
    private String url;
    private Map can_comment;
    private Long created;
    private Long updated;
    private Long id;
    private List upvoted_followees;

    public Boolean getAdmin_closed_comment() {
        return admin_closed_comment;
    }

    public void setAdmin_closed_comment(Boolean admin_closed_comment) {
        this.admin_closed_comment = admin_closed_comment;
    }

    public People getAuthor() {
        return author;
    }

    public void setAuthor(People author) {
        this.author = author;
    }

    public Integer getComment_count() {
        return comment_count;
    }

    public void setComment_count(Integer comment_count) {
        this.comment_count = comment_count;
    }

    public String getComment_permission() {
        return comment_permission;
    }

    public void setComment_permission(String comment_permission) {
        this.comment_permission = comment_permission;
    }

    public String getVoteup_count() {
        return voteup_count;
    }

    public void setVoteup_count(String voteup_count) {
        this.voteup_count = voteup_count;
    }

    public String getVoting() {
        return voting;
    }

    public void setVoting(String voting) {
        this.voting = voting;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getExcerpt_title() {
        return excerpt_title;
    }

    public void setExcerpt_title(String excerpt_title) {
        this.excerpt_title = excerpt_title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public Map getCan_comment() {
        return can_comment;
    }

    public void setCan_comment(Map can_comment) {
        this.can_comment = can_comment;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List getUpvoted_followees() {
        return upvoted_followees;
    }

    public void setUpvoted_followees(List upvoted_followees) {
        this.upvoted_followees = upvoted_followees;
    }
}

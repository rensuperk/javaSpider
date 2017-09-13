package ren.superk.zhihu.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document(indexName = "pins",type = "v1")
public class Pins {
     private boolean admin_closed_comment;
     private boolean is_admin_close_repin;
     private boolean is_deleted;
     private Integer like_count;
     private People author;
     private Integer comment_count;
     private Integer reaction_count;
     private Integer repin_count;
     private Long created;
     private Long updated;
     private String comment_permission;
     private String excerpt_title;
     @Id
     private String id;
     private String state;
     private String type;
     private String url;
     private String view_permission;
     private List comments = new ArrayList();
     private List content = new ArrayList();
     private List upvoted_followees = new ArrayList();
     private Map virtuals = new HashMap();

    public boolean isAdmin_closed_comment() {
        return admin_closed_comment;
    }

    public void setAdmin_closed_comment(boolean admin_closed_comment) {
        this.admin_closed_comment = admin_closed_comment;
    }

    public boolean isIs_admin_close_repin() {
        return is_admin_close_repin;
    }

    public void setIs_admin_close_repin(boolean is_admin_close_repin) {
        this.is_admin_close_repin = is_admin_close_repin;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
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

    public Integer getReaction_count() {
        return reaction_count;
    }

    public void setReaction_count(Integer reaction_count) {
        this.reaction_count = reaction_count;
    }

    public Integer getRepin_count() {
        return repin_count;
    }

    public void setRepin_count(Integer repin_count) {
        this.repin_count = repin_count;
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

    public String getComment_permission() {
        return comment_permission;
    }

    public void setComment_permission(String comment_permission) {
        this.comment_permission = comment_permission;
    }

    public String getExcerpt_title() {
        return excerpt_title;
    }

    public void setExcerpt_title(String excerpt_title) {
        this.excerpt_title = excerpt_title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getView_permission() {
        return view_permission;
    }

    public void setView_permission(String view_permission) {
        this.view_permission = view_permission;
    }

    public List getComments() {
        return comments;
    }

    public void setComments(List comments) {
        this.comments = comments;
    }

    public List getContent() {
        return content;
    }

    public void setContent(List content) {
        this.content = content;
    }

    public List getUpvoted_followees() {
        return upvoted_followees;
    }

    public void setUpvoted_followees(List upvoted_followees) {
        this.upvoted_followees = upvoted_followees;
    }

    public Map getVirtuals() {
        return virtuals;
    }

    public void setVirtuals(Map virtuals) {
        this.virtuals = virtuals;
    }
}

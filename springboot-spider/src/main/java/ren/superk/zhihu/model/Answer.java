package ren.superk.zhihu.model;

import java.util.List;

public class Answer {
    private Boolean admin_closed_comment;
    private Boolean is_collapsed;
    private Boolean is_copyable;
    private Boolean is_normal;

    private List annotation_action;
    private List mark_infos;
    private Question question;
    private People author;
    private Integer comment_count;
    private Integer voteup_count;
    private Long created_time;
    private Long updated_time;
    private Long id;
    private String collapse_reason;
    private String collapsed_by;
    private String comment_permission;
    private String content;
    private String excerpt;
    private String extras;
    private String reshipment_settings;
    private String url;
    private String thumbnail;
    private String type;

    public Boolean getAdmin_closed_comment() {
        return admin_closed_comment;
    }

    public void setAdmin_closed_comment(Boolean admin_closed_comment) {
        this.admin_closed_comment = admin_closed_comment;
    }

    public Boolean getIs_collapsed() {
        return is_collapsed;
    }

    public void setIs_collapsed(Boolean is_collapsed) {
        this.is_collapsed = is_collapsed;
    }

    public Boolean getIs_copyable() {
        return is_copyable;
    }

    public void setIs_copyable(Boolean is_copyable) {
        this.is_copyable = is_copyable;
    }

    public Boolean getIs_normal() {
        return is_normal;
    }

    public void setIs_normal(Boolean is_normal) {
        this.is_normal = is_normal;
    }

    public List getAnnotation_action() {
        return annotation_action;
    }

    public void setAnnotation_action(List annotation_action) {
        this.annotation_action = annotation_action;
    }

    public List getMark_infos() {
        return mark_infos;
    }

    public void setMark_infos(List mark_infos) {
        this.mark_infos = mark_infos;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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

    public Integer getVoteup_count() {
        return voteup_count;
    }

    public void setVoteup_count(Integer voteup_count) {
        this.voteup_count = voteup_count;
    }

    public Long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Long created_time) {
        this.created_time = created_time;
    }

    public Long getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(Long updated_time) {
        this.updated_time = updated_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollapse_reason() {
        return collapse_reason;
    }

    public void setCollapse_reason(String collapse_reason) {
        this.collapse_reason = collapse_reason;
    }

    public String getCollapsed_by() {
        return collapsed_by;
    }

    public void setCollapsed_by(String collapsed_by) {
        this.collapsed_by = collapsed_by;
    }

    public String getComment_permission() {
        return comment_permission;
    }

    public void setComment_permission(String comment_permission) {
        this.comment_permission = comment_permission;
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

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getReshipment_settings() {
        return reshipment_settings;
    }

    public void setReshipment_settings(String reshipment_settings) {
        this.reshipment_settings = reshipment_settings;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

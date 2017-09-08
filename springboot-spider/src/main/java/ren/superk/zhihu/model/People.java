package ren.superk.zhihu.model;


import java.util.ArrayList;
import java.util.List;

public class People {
    private List<Education> educations = new ArrayList<Education>();
    private List<Topic> locations = new ArrayList<Topic>();
    private List<Topic> badge = new ArrayList<Topic>();
    private List<Topic> employments = new ArrayList<Topic>();

    private Integer following_count;
    private Integer vote_from_count;
    private Integer favorite_count;
    private Integer pins_count;
    private Integer voteup_count;
    private Integer commercial_question_count;
    private Integer following_columns_count;
    private Integer participated_live_count;
    private Integer following_favlists_count;
    private Integer favorited_count;
    private Integer follower_count;
    private Integer following_topic_count;
    private Integer columns_count;
    private Integer hosted_live_count;
    private Integer thank_to_count;
    private Integer mutual_followees_count;
    private Integer marked_answers_count;
    private Integer thank_from_count;
    private Integer vote_to_count;
    private Integer answer_count;
    private Integer articles_count;
    private Integer question_count;
    private Integer logs_count;
    private Integer following_question_count;
    private Integer thanked_count;
    private Integer gender;

    private String user_type;
    private String marked_answers_text;
    private String id;
    private String headline;
    private String cover_url;
    private String url_token;
    private String avatar_hue;
    private String description;
    private String type;
    private String avatar_url;
    private String name;
    private String url;
    private String message_thread_token;

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<Topic> getLocations() {
        return locations;
    }

    public void setLocations(List<Topic> locations) {
        this.locations = locations;
    }

    public List<Topic> getBadge() {
        return badge;
    }

    public void setBadge(List<Topic> badge) {
        this.badge = badge;
    }

    public List<Topic> getEmployments() {
        return employments;
    }

    public void setEmployments(List<Topic> employments) {
        this.employments = employments;
    }

    public Integer getFollowing_count() {
        return following_count;
    }

    public void setFollowing_count(Integer following_count) {
        this.following_count = following_count;
    }

    public Integer getVote_from_count() {
        return vote_from_count;
    }

    public void setVote_from_count(Integer vote_from_count) {
        this.vote_from_count = vote_from_count;
    }

    public Integer getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(Integer favorite_count) {
        this.favorite_count = favorite_count;
    }

    public Integer getPins_count() {
        return pins_count;
    }

    public void setPins_count(Integer pins_count) {
        this.pins_count = pins_count;
    }

    public Integer getVoteup_count() {
        return voteup_count;
    }

    public void setVoteup_count(Integer voteup_count) {
        this.voteup_count = voteup_count;
    }

    public Integer getCommercial_question_count() {
        return commercial_question_count;
    }

    public void setCommercial_question_count(Integer commercial_question_count) {
        this.commercial_question_count = commercial_question_count;
    }

    public Integer getFollowing_columns_count() {
        return following_columns_count;
    }

    public void setFollowing_columns_count(Integer following_columns_count) {
        this.following_columns_count = following_columns_count;
    }

    public Integer getParticipated_live_count() {
        return participated_live_count;
    }

    public void setParticipated_live_count(Integer participated_live_count) {
        this.participated_live_count = participated_live_count;
    }

    public Integer getFollowing_favlists_count() {
        return following_favlists_count;
    }

    public void setFollowing_favlists_count(Integer following_favlists_count) {
        this.following_favlists_count = following_favlists_count;
    }

    public Integer getFavorited_count() {
        return favorited_count;
    }

    public void setFavorited_count(Integer favorited_count) {
        this.favorited_count = favorited_count;
    }

    public Integer getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(Integer follower_count) {
        this.follower_count = follower_count;
    }

    public Integer getFollowing_topic_count() {
        return following_topic_count;
    }

    public void setFollowing_topic_count(Integer following_topic_count) {
        this.following_topic_count = following_topic_count;
    }

    public Integer getColumns_count() {
        return columns_count;
    }

    public void setColumns_count(Integer columns_count) {
        this.columns_count = columns_count;
    }

    public Integer getHosted_live_count() {
        return hosted_live_count;
    }

    public void setHosted_live_count(Integer hosted_live_count) {
        this.hosted_live_count = hosted_live_count;
    }

    public Integer getThank_to_count() {
        return thank_to_count;
    }

    public void setThank_to_count(Integer thank_to_count) {
        this.thank_to_count = thank_to_count;
    }

    public Integer getMutual_followees_count() {
        return mutual_followees_count;
    }

    public void setMutual_followees_count(Integer mutual_followees_count) {
        this.mutual_followees_count = mutual_followees_count;
    }

    public Integer getMarked_answers_count() {
        return marked_answers_count;
    }

    public void setMarked_answers_count(Integer marked_answers_count) {
        this.marked_answers_count = marked_answers_count;
    }

    public Integer getThank_from_count() {
        return thank_from_count;
    }

    public void setThank_from_count(Integer thank_from_count) {
        this.thank_from_count = thank_from_count;
    }

    public Integer getVote_to_count() {
        return vote_to_count;
    }

    public void setVote_to_count(Integer vote_to_count) {
        this.vote_to_count = vote_to_count;
    }

    public Integer getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(Integer answer_count) {
        this.answer_count = answer_count;
    }

    public Integer getArticles_count() {
        return articles_count;
    }

    public void setArticles_count(Integer articles_count) {
        this.articles_count = articles_count;
    }

    public Integer getQuestion_count() {
        return question_count;
    }

    public void setQuestion_count(Integer question_count) {
        this.question_count = question_count;
    }

    public Integer getLogs_count() {
        return logs_count;
    }

    public void setLogs_count(Integer logs_count) {
        this.logs_count = logs_count;
    }

    public Integer getFollowing_question_count() {
        return following_question_count;
    }

    public void setFollowing_question_count(Integer following_question_count) {
        this.following_question_count = following_question_count;
    }

    public Integer getThanked_count() {
        return thanked_count;
    }

    public void setThanked_count(Integer thanked_count) {
        this.thanked_count = thanked_count;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getMarked_answers_text() {
        return marked_answers_text;
    }

    public void setMarked_answers_text(String marked_answers_text) {
        this.marked_answers_text = marked_answers_text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getUrl_token() {
        return url_token;
    }

    public void setUrl_token(String url_token) {
        this.url_token = url_token;
    }

    public String getAvatar_hue() {
        return avatar_hue;
    }

    public void setAvatar_hue(String avatar_hue) {
        this.avatar_hue = avatar_hue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage_thread_token() {
        return message_thread_token;
    }

    public void setMessage_thread_token(String message_thread_token) {
        this.message_thread_token = message_thread_token;
    }
}

package code.pojo;

import java.util.Date;

public class BlogArticle {
    private int id;
    private Date updateTime;
    private Date createTime;
    private String title;
    private String content;
    private int viewTimes;
    private int state;
    private int userId;
    private int articleKindId;
    private int blogKindId;
    private String headUrl;
    private String nickname;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(int viewTimes) {
        this.viewTimes = viewTimes;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getArticleKindId() {
        return articleKindId;
    }

    public void setArticleKindId(int articleKindId) {
        this.articleKindId = articleKindId;
    }

    public int getBlogKindId() {
        return blogKindId;
    }

    public void setBlogKindId(int blogKindId) {
        this.blogKindId = blogKindId;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

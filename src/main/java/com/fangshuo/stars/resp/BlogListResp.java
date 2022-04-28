package com.fangshuo.stars.resp;


import java.sql.Timestamp;
import java.util.Date;

public class BlogListResp {
    Long id;
    Long authorId;
    String authorName;
    Timestamp publishTime;
    String content;
    Integer voteNum;
    String avatar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVoteNum() {
        return voteNum;
    }

    public void setVoteNum(Integer voteNum) {
        this.voteNum = voteNum;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public BlogListResp() {
    }

    public BlogListResp(Long id, Long authorId, String authorName, Timestamp publishTime, String content, Integer voteNum, String avatar) {
        this.id = id;
        this.authorId = authorId;
        this.authorName = authorName;
        this.publishTime = publishTime;
        this.content = content;
        this.voteNum = voteNum;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "BlogListResp{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", publishTime=" + publishTime +
                ", content='" + content + '\'' +
                ", voteNum=" + voteNum +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}

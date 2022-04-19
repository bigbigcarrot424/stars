package com.fangshuo.stars.domain;


import java.util.Date;

public class Blog {
    Long id;
    Long authorId;
    Date publishTime;
    String content;
    Integer voteNum;

    public Blog(Long id, Long authorId, Date publishTime, String content, Integer voteNum) {
        this.id = id;
        this.authorId = authorId;
        this.publishTime = publishTime;
        this.content = content;
        this.voteNum = voteNum;
    }

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

    public void setPublishTime(Date publishTime) {
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

    public Blog() {
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", publishTime=" + publishTime +
                ", content='" + content + '\'' +
                ", voteNum=" + voteNum +
                '}';
    }
}

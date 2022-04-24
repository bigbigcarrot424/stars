package com.fangshuo.stars.req;

import java.sql.Timestamp;

public class BlogSaveReq {
    Long authorId;
    String content;
    Timestamp timestamp;

    public BlogSaveReq(Long authorId, String content, Timestamp timestamp) {
        this.authorId = authorId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public BlogSaveReq() {
    }

    @Override
    public String toString() {
        return "BlogSaveReq{" +
                "authorId=" + authorId +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

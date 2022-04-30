package com.fangshuo.stars.mapper;

import com.fangshuo.stars.domain.Comment;
import com.fangshuo.stars.resp.CommentListResp;

import java.util.List;

public interface CommentMapper {
    public List<CommentListResp> getCommentList(Long blogId);

    public void save(Comment comment);

}

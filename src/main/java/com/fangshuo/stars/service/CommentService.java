package com.fangshuo.stars.service;

import com.fangshuo.stars.domain.Comment;
import com.fangshuo.stars.mapper.CommentMapper;
import com.fangshuo.stars.req.CommentSaveReq;
import com.fangshuo.stars.resp.CommentListResp;
import com.fangshuo.stars.util.CopyUtil;
import com.fangshuo.stars.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    private static final Logger LOG = LoggerFactory.getLogger(CommentService.class);

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private BlogService blogService;

    @Resource
    private SnowFlake snowFlake;

    public List<CommentListResp> list(Long blogId){
        List<CommentListResp> commentList = commentMapper.getCommentList(blogId);
        return commentList;
    }

    public Integer commentNum(Long blogId){
        List<CommentListResp> commentList = commentMapper.getCommentList(blogId);
        return commentList.size();
    }


    public void save(CommentSaveReq req){
        LOG.info("req:{}",req);
        Comment comment = CopyUtil.copy(req, Comment.class);

        if (ObjectUtils.isEmpty(comment.getId())){
            long commentId = snowFlake.nextId();
            comment.setId(commentId);
        }
        comment.setCommentTime(new Timestamp(new Date().getTime()));
        commentMapper.save(comment);
        blogService.increaseComment(req.getBlogId());
    }

    public void deleteByBlogId(Long blogId){
        LOG.info("删除帖子{}评论",blogId);
        commentMapper.deleteByBlogId(blogId);
    }
}

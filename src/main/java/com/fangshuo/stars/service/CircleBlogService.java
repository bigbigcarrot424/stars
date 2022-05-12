package com.fangshuo.stars.service;

import com.fangshuo.stars.domain.Blog;
import com.fangshuo.stars.domain.CircleBlog;
import com.fangshuo.stars.mapper.BlogMapper;
import com.fangshuo.stars.mapper.CircleBlogMapper;
import com.fangshuo.stars.req.BlogEditReq;
import com.fangshuo.stars.req.BlogSaveReq;
import com.fangshuo.stars.req.CircleBlogSaveReq;
import com.fangshuo.stars.resp.BlogListResp;
import com.fangshuo.stars.resp.UserInfoResp;
import com.fangshuo.stars.util.CopyUtil;
import com.fangshuo.stars.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CircleBlogService {
    private static final Logger LOG = LoggerFactory.getLogger(CircleBlogService.class);

    @Resource
    private CircleBlogMapper circleBlogMapper;

    @Resource
    private CommentService commentService;

    @Resource
    private LikesService likesService;

    @Resource
    private FollowService followService;

    @Resource
    private SnowFlake snowFlake;



    //存储兴趣圈帖子
    public void saveCircleBlog(CircleBlogSaveReq req){
        CircleBlog circleBlog = CopyUtil.copy(req, CircleBlog.class);

        if (ObjectUtils.isEmpty(circleBlog.getId())){
            long blogId = snowFlake.nextId();
            circleBlog.setId(blogId);
        }
        circleBlog.setPublishTime(new Timestamp(new Date().getTime()));
        circleBlog.setVoteNum(0);
        circleBlogMapper.saveCircleBlog(circleBlog);
    }

    //删除帖子
    public void deleteById(Long blogId){
        LOG.info("删除帖子id：{}",blogId);
        commentService.deleteByBlogId(blogId);
        likesService.deleteByBlogId(blogId);
        circleBlogMapper.deleteById(blogId);
    }

    public void increaseComment(Long blogId){
        circleBlogMapper.increaseComment(blogId);
    }

    public List<BlogListResp> blogListInCircle(Long circleId){
        List<BlogListResp> blogListByCircleId = circleBlogMapper.getBlogByCircleId(circleId);
        return blogListByCircleId;
    }

    public Integer blogListInCircleNum(Long circleId){
        List<BlogListResp> blogListByCircleId = circleBlogMapper.getBlogByCircleId(circleId);
        return blogListByCircleId.size();
    }

    public List<BlogListResp> blogListInCircleByPage(Long circleId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<BlogListResp> blogListByCircleId = circleBlogMapper.getBlogByCircleId(circleId);
        return blogListByCircleId;
    }

}

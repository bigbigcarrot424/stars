package com.fangshuo.stars.service;

import com.fangshuo.stars.domain.Blog;
import com.fangshuo.stars.domain.Circle;
import com.fangshuo.stars.domain.CircleBlog;
import com.fangshuo.stars.mapper.BlogMapper;
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
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    private static final Logger LOG = LoggerFactory.getLogger(BlogService.class);

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private CommentService commentService;

    @Resource
    private LikesService likesService;

    @Resource
    private FollowService followService;

    @Resource
    private SnowFlake snowFlake;

    //获取所有帖子
    public List<BlogListResp> list(){
        List<BlogListResp> blogList = blogMapper.getBlogList();
        return blogList;
    }

    //获取我的帖子
    public List<BlogListResp> myList(Long userId){
        List<BlogListResp> blogList = blogMapper.getMyBlogList(userId);
        return blogList;
    }

    //获取我关注人的所有帖子
    public List<BlogListResp> followBlogList(List<UserInfoResp> followUserList){
        List<BlogListResp> blogList = blogMapper.followBlogList(followUserList);
        return blogList;
    }

    //获取所有帖子数量
    public Integer blogNum(){
        List<BlogListResp> blogList = blogMapper.getBlogList();
        return blogList.size();
    }

    //我的帖子数量
    public Integer myBlogNum(Long userId){
        List<BlogListResp> blogList = blogMapper.getMyBlogList(userId);
        return blogList.size();
    }

    //我关注的人们的帖子数量
    public Integer myFollowBlogNum(Long userId){
        List<UserInfoResp> userInfoRespList = followService.followList(userId.toString());
        List<BlogListResp> blogListResps = blogMapper.followBlogList(userInfoRespList);
        return blogListResps.size();
    }

    //所有帖子分页
    public List<BlogListResp> getBlogListByPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<BlogListResp> list = this.list();
        return list;
    }

    //我的发帖（分页）
    public List<BlogListResp> getMyBlogListByPage(int pageNum, int pageSize, Long userId){
        PageHelper.startPage(pageNum, pageSize);
        List<BlogListResp> list = this.myList(userId);
        return list;
    }

    //关注的人发贴（分页）
    public List<BlogListResp> followBlogListByPage(int pageNum, int pageSize, Long userId){
        List<UserInfoResp> followUserList = followService.followList(userId.toString());
        PageHelper.startPage(pageNum, pageSize);
        List<BlogListResp> list = this.followBlogList(followUserList);
        return list;
    }

    //通过帖子Id找到帖子
    public BlogListResp getBlogById(Long blogId){
        BlogListResp blogById = blogMapper.getBlogById(blogId);
        return blogById;
    }

    //存储帖子
    public void save(BlogSaveReq req){
        Blog blog = CopyUtil.copy(req, Blog.class);

        if (ObjectUtils.isEmpty(blog.getId())){
            long blogId = snowFlake.nextId();
            blog.setId(blogId);
        }
        blog.setPublishTime(new Timestamp(new Date().getTime()));
        blog.setVoteNum(0);
        blogMapper.save(blog);
    }

    //编辑帖子
    public void edit(BlogEditReq req){
        blogMapper.editBlogById(req.getBlogId(), req.getContent());
    }

    //删除帖子
    public void deleteById(Long blogId){
        LOG.info("删除帖子id：{}",blogId);
        commentService.deleteByBlogId(blogId);
        likesService.deleteByBlogId(blogId);
        blogMapper.deleteById(blogId);
    }

    public void increaseComment(Long blogId){
        blogMapper.increaseComment(blogId);
    }

}

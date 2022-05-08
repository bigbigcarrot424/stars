package com.fangshuo.stars.service;

import com.fangshuo.stars.domain.Blog;
import com.fangshuo.stars.mapper.BlogMapper;
import com.fangshuo.stars.req.BlogEditReq;
import com.fangshuo.stars.req.BlogSaveReq;
import com.fangshuo.stars.resp.BlogListResp;
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
public class BlogService {
    private static final Logger LOG = LoggerFactory.getLogger(BlogService.class);

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private SnowFlake snowFlake;

    public List<BlogListResp> list(){
        List<BlogListResp> blogList = blogMapper.getBlogList();
        return blogList;
    }

    public List<BlogListResp> myList(Long userId){
        List<BlogListResp> blogList = blogMapper.getMyBlogList(userId);
        return blogList;
    }

    public Integer blogNum(){
        List<BlogListResp> blogList = blogMapper.getBlogList();
        return blogList.size();
    }

    public Integer myBlogNum(Long userId){
        List<BlogListResp> blogList = blogMapper.getMyBlogList(userId);
        return blogList.size();
    }

    public List<BlogListResp> getBlogListByPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<BlogListResp> list = this.list();
        return list;
    }

    public List<BlogListResp> getMyBlogListByPage(int pageNum, int pageSize, Long userId){
        PageHelper.startPage(pageNum, pageSize);
        List<BlogListResp> list = this.myList(userId);
        return list;
    }

    public Blog getBlogById(Long blogId){
        Blog blogById = blogMapper.getBlogById(blogId);
        return blogById;
    }

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


    public void edit(BlogEditReq req){
        blogMapper.editBlogById(req.getBlogId(), req.getContent());
    }

    public void increaseComment(Long blogId){
        blogMapper.increaseComment(blogId);
    }


}

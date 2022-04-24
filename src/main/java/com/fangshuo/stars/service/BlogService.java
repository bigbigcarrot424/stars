package com.fangshuo.stars.service;

import com.fangshuo.stars.domain.Blog;
import com.fangshuo.stars.mapper.BlogMapper;
import com.fangshuo.stars.req.BlogSaveReq;
import com.fangshuo.stars.util.CopyUtil;
import com.fangshuo.stars.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Resource
    private BlogMapper blogMapper;

    @Resource
    private SnowFlake snowFlake;

    Date date = new Date();

    public List<Blog> list(){
        List<Blog> blog = blogMapper.getBlogList();
        return blog;
    }


    public void save(BlogSaveReq req){
        Blog blog = CopyUtil.copy(req, Blog.class);

        if (ObjectUtils.isEmpty(blog.getId())){
            long blogId = snowFlake.nextId();
            blog.setId(blogId);
        }
        blog.setPublishTime(new Timestamp(date.getTime()));
        blog.setVoteNum(0);
        blogMapper.save(blog);
    }
}

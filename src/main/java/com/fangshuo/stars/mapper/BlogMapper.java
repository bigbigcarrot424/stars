package com.fangshuo.stars.mapper;

import com.fangshuo.stars.domain.Blog;
import com.fangshuo.stars.domain.CircleBlog;
import com.fangshuo.stars.resp.BlogListResp;
import com.fangshuo.stars.resp.UserInfoResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {
    public List<BlogListResp> getBlogList();

    public void save(Blog blog);

    public void increaseComment(Long id);

    public List<BlogListResp> getMyBlogList(Long id);

    public BlogListResp getBlogById(Long id);

    public void editBlogById(Long id, String content);

    public void deleteById(Long blogId);

    public List<BlogListResp> followBlogList(@Param("listParam") List<UserInfoResp> followUserList);

}

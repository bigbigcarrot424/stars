package com.fangshuo.stars.mapper;

import com.fangshuo.stars.domain.Blog;
import com.fangshuo.stars.resp.BlogListResp;

import java.util.List;

public interface BlogMapper {
    public List<BlogListResp> getBlogList();

    public void save(Blog blog);
}

package com.fangshuo.stars.mapper;

import com.fangshuo.stars.domain.Blog;

import java.util.List;

public interface BlogMapper {
    public List<Blog> getBlogList();

    public void save(Blog blog);
}

package com.fangshuo.stars.mapper;

import com.fangshuo.stars.domain.CircleBlog;
import com.fangshuo.stars.resp.BlogListResp;

import java.util.List;

public interface CircleBlogMapper {

    public void saveCircleBlog(CircleBlog circleBlog);

    public void increaseComment(Long id);

    public void deleteById(Long circleCircleBlogId);

    public List<BlogListResp> getBlogByCircleId(Long circleId);
}

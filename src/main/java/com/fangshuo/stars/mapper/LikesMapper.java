package com.fangshuo.stars.mapper;

import com.fangshuo.stars.domain.Likes;
import com.fangshuo.stars.resp.LikesListByUserResp;

import java.util.List;

public interface LikesMapper {
    public List<LikesListByUserResp> getLikesListByUser(Long userId);

    public void save(Likes likes);

    public List<Likes> getLikesByUserAndBlog(Likes likes);

    public void deleteByBlogId(Long blogId);
}

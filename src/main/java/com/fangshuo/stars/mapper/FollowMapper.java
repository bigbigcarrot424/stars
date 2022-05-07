package com.fangshuo.stars.mapper;

import com.fangshuo.stars.domain.Follow;


public interface FollowMapper {
    public void follow(Follow follow);
    public void unfollow(Long likeId, Long belikedId);
    public Follow ifFollowed(Long likeId, Long belikedId);
}

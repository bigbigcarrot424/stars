package com.fangshuo.stars.service;

import com.fangshuo.stars.domain.Follow;
import com.fangshuo.stars.mapper.FollowMapper;
import com.fangshuo.stars.mapper.UserMapper;
import com.fangshuo.stars.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class FollowService {
    private static final Logger LOG = LoggerFactory.getLogger(FollowService.class);
    @Resource
    private FollowMapper followMapper;
    @Resource
    private SnowFlake snowFlake;

    public Follow follow(String likeId, String belikedId){
        Follow follow = new Follow(snowFlake.nextId(),Long.parseLong(likeId), Long.parseLong(belikedId),new Timestamp(new Date().getTime()));
        followMapper.follow(follow);
        return follow;
    }

    public void unfollow(String likeId, String belikedId){
        followMapper.unfollow(Long.parseLong(likeId), Long.parseLong(belikedId));
    }

    public Follow ifFollowed(String likeId, String belikedId){
        Follow follow = followMapper.ifFollowed(Long.parseLong(likeId), Long.parseLong(belikedId));
        return follow;
    }

}

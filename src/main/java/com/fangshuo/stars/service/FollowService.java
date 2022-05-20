package com.fangshuo.stars.service;

import com.fangshuo.stars.domain.Follow;
import com.fangshuo.stars.exception.BusinessException;
import com.fangshuo.stars.exception.BusinessExceptionCode;
import com.fangshuo.stars.mapper.FollowMapper;
import com.fangshuo.stars.mapper.UserMapper;
import com.fangshuo.stars.resp.UserInfoResp;
import com.fangshuo.stars.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FollowService {
    private static final Logger LOG = LoggerFactory.getLogger(FollowService.class);
    @Resource
    private FollowMapper followMapper;
    @Resource
    private SnowFlake snowFlake;
    @Resource
    private UserService userService;

    public Follow follow(String likeId, String belikedId){
        Follow follow1 = followMapper.ifFollowed(Long.parseLong(likeId), Long.parseLong(belikedId));
        if (ObjectUtils.isEmpty(follow1)){
            Follow follow = new Follow(snowFlake.nextId(),Long.parseLong(likeId), Long.parseLong(belikedId),new Timestamp(new Date().getTime()));
            followMapper.follow(follow);
            return follow;
        }else {
            throw new BusinessException(BusinessExceptionCode.FOLLOW_REPEAT);
        }
    }

    public List<UserInfoResp> followList(String likeId){
        List<Follow> follows = followMapper.followList(Long.parseLong(likeId));
        List<UserInfoResp> userInfoRespList = new ArrayList<>();
        for (Follow follow:
             follows) {
            UserInfoResp userInfo = userService.getUserInfo(follow.getBelikedId());
            userInfoRespList.add(userInfo);
        }
        return userInfoRespList;
    }

    public void unfollow(String likeId, String belikedId){
        followMapper.unfollow(Long.parseLong(likeId), Long.parseLong(belikedId));
    }

    public Follow ifFollowed(String likeId, String belikedId){
        Follow follow = followMapper.ifFollowed(Long.parseLong(likeId), Long.parseLong(belikedId));
        return follow;
    }

}

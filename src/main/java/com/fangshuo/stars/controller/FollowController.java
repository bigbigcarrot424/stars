package com.fangshuo.stars.controller;

import com.alibaba.fastjson.JSONObject;
import com.fangshuo.stars.domain.Follow;
import com.fangshuo.stars.domain.User;
import com.fangshuo.stars.domain.UserInfo;
import com.fangshuo.stars.req.UserLoginReq;
import com.fangshuo.stars.req.UserSignUpReq;
import com.fangshuo.stars.resp.CommonResp;
import com.fangshuo.stars.resp.UserInfoResp;
import com.fangshuo.stars.resp.UserLoginResp;
import com.fangshuo.stars.resp.UserSignUpResp;
import com.fangshuo.stars.service.FollowService;
import com.fangshuo.stars.service.UserService;
import com.fangshuo.stars.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/follow")
public class FollowController {
    private static final Logger LOG = LoggerFactory.getLogger(FollowController.class);

    @Resource
    private FollowService followService;

    //关注
    @GetMapping("/follow/{likeId}/{belikedId}")
    public CommonResp<UserInfoResp> follow(@PathVariable String likeId, @PathVariable String belikedId){
        CommonResp resp = new CommonResp<>();
        Follow follow = followService.follow(likeId, belikedId);
        resp.setContent(follow);
        return resp;
    }

    //取消关注
    @GetMapping("/unfollow/{likeId}/{belikedId}")
    public CommonResp<UserInfoResp> unfollow(@PathVariable String likeId, @PathVariable String belikedId){
        CommonResp resp = new CommonResp<>();
        followService.unfollow(likeId, belikedId);
        return resp;
    }

    //是否关注
    @GetMapping("/ifFollowed/{likeId}/{belikedId}")
    public CommonResp<UserInfoResp> ifFollowed(@PathVariable String likeId, @PathVariable String belikedId){
        CommonResp resp = new CommonResp<>();
        Follow follow = followService.ifFollowed(likeId, belikedId);
        if (ObjectUtils.isEmpty(follow)){
            resp.setContent(false);
        }else {
            resp.setContent(follow);
        }
        return resp;
    }

}

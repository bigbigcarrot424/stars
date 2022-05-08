package com.fangshuo.stars.controller;

import com.fangshuo.stars.domain.Follow;
import com.fangshuo.stars.resp.CommonResp;
import com.fangshuo.stars.resp.UserInfoResp;
import com.fangshuo.stars.service.FollowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    //关注列表
    @GetMapping("/followList/{likeId}")
    public CommonResp<UserInfoResp> followList(@PathVariable String likeId){
        CommonResp resp = new CommonResp<>();
        List<UserInfoResp> userFollowRespList = followService.followList(likeId);
        resp.setContent(userFollowRespList);
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

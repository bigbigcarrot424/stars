package com.fangshuo.stars.controller;

import com.alibaba.fastjson.JSONObject;
import com.fangshuo.stars.domain.User;
import com.fangshuo.stars.req.UserLoginReq;
import com.fangshuo.stars.req.UserSignUpReq;
import com.fangshuo.stars.resp.CommonResp;
import com.fangshuo.stars.resp.UserLoginResp;
import com.fangshuo.stars.resp.UserSignUpResp;
import com.fangshuo.stars.service.UserService;
import com.fangshuo.stars.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/list")
    public List<User> userList() {
        return userService.list();
    }

    @PostMapping("/signup")
    public CommonResp<UserSignUpResp> signUp(@Valid @RequestBody UserSignUpReq req){
//        密码加密
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        LOG.info("加密后的密码：{}",req.getPassword());
        UserSignUpResp userSignUpResp = userService.signUp(req);
        CommonResp<UserSignUpResp> resp = new CommonResp<>();
        resp.setContent(userSignUpResp);
        return resp;
    }

    @PostMapping("/login")
    public CommonResp<UserLoginResp> login(@Valid @RequestBody UserLoginReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp<UserLoginResp> resp = new CommonResp<>();
        UserLoginResp userLoginResp = userService.login(req);

        Long token = snowFlake.nextId();
        LOG.info("生成单点登录token: {}，并放入redis中", token);
        userLoginResp.setToken(token.toString());
        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginResp), 3600 * 24, TimeUnit.SECONDS);

        resp.setContent(userLoginResp);
        return resp;
    }
}

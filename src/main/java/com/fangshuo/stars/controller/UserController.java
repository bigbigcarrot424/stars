package com.fangshuo.stars.controller;

import com.alibaba.fastjson.JSONObject;
import com.fangshuo.stars.domain.User;
import com.fangshuo.stars.domain.UserInfo;
import com.fangshuo.stars.req.UserInfoUpdateReq;
import com.fangshuo.stars.req.UserLoginReq;
import com.fangshuo.stars.req.UserSignUpReq;
import com.fangshuo.stars.resp.CommonResp;
import com.fangshuo.stars.resp.UserInfoResp;
import com.fangshuo.stars.resp.UserLoginResp;
import com.fangshuo.stars.resp.UserSignUpResp;
import com.fangshuo.stars.service.UserService;
import com.fangshuo.stars.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
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

    //查找用户列表
    @GetMapping("/list")
    public List<User> userList() {
        return userService.list();
    }

    //用户注册
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

    //用户登录
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

    //用户登出
    @GetMapping("/logout/{token}")
    public CommonResp<UserLoginResp> logout(@PathVariable String token){
        CommonResp resp = new CommonResp<>();
        redisTemplate.delete(token);
        LOG.info("从redis中删除token: {}", token);
        return resp;
    }

    //查询用户信息
    @GetMapping("/info/{userId}")
    public CommonResp<UserInfoResp> info(@PathVariable Long userId){
        CommonResp resp = new CommonResp<>();
        UserInfoResp userInfo = userService.getUserInfo(userId);
        resp.setContent(userInfo);
        return resp;
    }

    //修改用户信息
    @PostMapping("/updateInfo")
    public CommonResp<UserInfoResp> updateInfo(@RequestBody UserInfoUpdateReq req){
        CommonResp resp = new CommonResp<>();
        userService.updateUserInfo(req);
        return resp;
    }

    //修改用户头像
    @RequestMapping("/upload/avatar")
    public CommonResp upload(@RequestHeader(value = "userId",required = false) String userId, @RequestParam MultipartFile avatar) throws IOException {
        LOG.info("上传文件开始：{}", avatar);
        LOG.info("文件名：{}", avatar.getOriginalFilename());
        LOG.info("文件大小：{}", avatar.getSize());

        // 保存文件到本地
        String fileName = avatar.getOriginalFilename();
        //点.需要转义，一个反斜线还不行，要两个反斜线
        String[] types = fileName.split("\\.");
        LOG.info("types:{}",types[1]);
        LOG.info("userID:{}", userId);
        fileName = userId.concat(".").concat(types[1]);
        String fullPath = "D:/file/stars/avatar/" + fileName;
        File dest = new File(fullPath);
        avatar.transferTo(dest);
        LOG.info(dest.getAbsolutePath());

        //修改用户头像
        userService.alterAvatarName(fileName, userId);

        return new CommonResp();
    }
}

package com.fangshuo.stars.controller;

import com.fangshuo.stars.domain.User;
import com.fangshuo.stars.req.UserSignUpReq;
import com.fangshuo.stars.resp.CommonResp;
import com.fangshuo.stars.resp.UserSignUpResp;
import com.fangshuo.stars.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/list")
    public List<User> userList() {
        return userService.list();
    }

    @PostMapping("/signup")
    public CommonResp<UserSignUpResp> signUp(@RequestBody UserSignUpReq req){
        UserSignUpResp userSignUpResp = userService.signUp(req);
        CommonResp<UserSignUpResp> resp = new CommonResp<>();
        resp.setContent(userSignUpResp);
        return resp;
    }
}

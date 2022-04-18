package com.fangshuo.stars.controller;

import com.fangshuo.stars.domain.User;
import com.fangshuo.stars.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/user/list")
    public List<User> userList() {
        return userService.list();
    }
}

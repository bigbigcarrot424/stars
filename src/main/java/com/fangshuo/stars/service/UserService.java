package com.fangshuo.stars.service;

import com.fangshuo.stars.domain.User;
import com.fangshuo.stars.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public List<User> list(){
        List<User> user = userMapper.getUserList();
        return user;
    }
}

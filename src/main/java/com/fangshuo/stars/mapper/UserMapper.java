package com.fangshuo.stars.mapper;

import com.fangshuo.stars.domain.User;

import java.util.List;

public interface UserMapper {
    public List<User> getUserList();
    public User selectUserByUsername(String username);
    public void insertUser(User user);
}

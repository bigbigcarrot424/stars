package com.fangshuo.stars.mapper;

import com.fangshuo.stars.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    public List<User> getUserList();
    public User selectUserByUsername(String username);
    public void insertUser(User user);
    public void alterAvatarName(@Param("avatarName")String avatarName, @Param("userId")Long userId);
}

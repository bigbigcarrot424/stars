package com.fangshuo.stars.mapper;

import com.fangshuo.stars.domain.User;
import com.fangshuo.stars.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {
    public UserInfo getUserInfo(@Param("userId")Long userId);
    public void updateUserInfo(UserInfo userInfo);
    public void insertUserInfo(UserInfo userInfo);
}

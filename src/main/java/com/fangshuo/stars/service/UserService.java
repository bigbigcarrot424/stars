package com.fangshuo.stars.service;

import com.fangshuo.stars.domain.User;
import com.fangshuo.stars.domain.UserInfo;
import com.fangshuo.stars.exception.BusinessException;
import com.fangshuo.stars.exception.BusinessExceptionCode;
import com.fangshuo.stars.mapper.UserInfoMapper;
import com.fangshuo.stars.mapper.UserMapper;
import com.fangshuo.stars.req.UserInfoUpdateReq;
import com.fangshuo.stars.req.UserLoginReq;
import com.fangshuo.stars.req.UserSignUpReq;
import com.fangshuo.stars.resp.UserInfoResp;
import com.fangshuo.stars.resp.UserLoginResp;
import com.fangshuo.stars.resp.UserSignUpResp;
import com.fangshuo.stars.util.CopyUtil;
import com.fangshuo.stars.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private SnowFlake snowFlake;

    public List<User> list(){
        List<User> user = userMapper.getUserList();
        return user;
    }

    public UserSignUpResp signUp(UserSignUpReq req){
        User user = CopyUtil.copy(req, User.class);
        LOG.info("用户注册开始！");
        User userDb = userMapper.selectUserByUsername(req.getUsername());
        if (ObjectUtils.isEmpty(userDb)){
            user.setId(snowFlake.nextId());
            user.setAvatar("./default.png");
            userMapper.insertUser(user);
            UserSignUpResp userSignUpResp = CopyUtil.copy(user, UserSignUpResp.class);
            return userSignUpResp;
        }else {
            throw new BusinessException(BusinessExceptionCode.USER_NAME_EXIST);
        }
    }

    public UserLoginResp login(UserLoginReq req){
        LOG.info("用户登录");
        User userDb = userMapper.selectUserByUsername(req.getUsername());
        if (!ObjectUtils.isEmpty(userDb)){
            if(req.getPassword().equals(userDb.getPassword())){
                UserLoginResp userLoginResp = CopyUtil.copy(userDb, UserLoginResp.class);
                return userLoginResp;
            }else {
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }else {
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }
    }

    public void alterAvatarName(String avatarName, String userId){
        userMapper.alterAvatarName(avatarName, Long.parseLong(userId));
    }

    public UserInfoResp getUserInfo(Long userId){
        UserInfo userInfoDetail = userInfoMapper.getUserInfo(userId);
        UserInfoResp resp;
        if (ObjectUtils.isEmpty(userInfoDetail)){
            resp = new UserInfoResp();
        }else {
            resp = CopyUtil.copy(userInfoDetail, UserInfoResp.class);
        }
        User userById = userMapper.getUserById(userId);
        resp.setId(userById.getId());
        resp.setUsername(userById.getUsername());
        resp.setName(userById.getName());
        resp.setAvatar(userById.getAvatar());
        return resp;
    }

    public UserInfoResp getUserInfoByName(String name){
        Long userId = userMapper.getUserIdByName(name);
        UserInfoResp userInfo = getUserInfo(userId);
        return userInfo;
    }

    public List<UserInfoResp> getUserListByTags(String tag){
        String selectTag = '%' + tag + '%';
        List<Long> userIdByTag = userInfoMapper.getUserIdByTag(selectTag);
        LOG.info("查询标签：{}", selectTag);
        List<UserInfoResp> userInfoRespList = new ArrayList<>();
        for (Long userId : userIdByTag) {
            UserInfoResp userInfo = getUserInfo(userId);
            userInfoRespList.add(userInfo);
        }
        return userInfoRespList;
    }

    public Long getUserIdByName(String name){
        Long userIdByName = userMapper.getUserIdByName(name);
        return userIdByName;
    }

    public void updateUserInfo(UserInfoUpdateReq userInfoReq){
        UserInfo userInfo = userInfoMapper.getUserInfo(userInfoReq.getId());
        if(ObjectUtils.isEmpty(userInfo)){
            userInfoMapper.insertUserInfo(userInfo);
        }else {
            userInfoMapper.updateUserInfo(userInfo);
            userMapper.updateName(userInfo.getId(), userInfoReq.getName());
        }
    }

}

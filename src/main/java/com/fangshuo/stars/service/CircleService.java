package com.fangshuo.stars.service;

import com.fangshuo.stars.domain.Circle;
import com.fangshuo.stars.domain.User;
import com.fangshuo.stars.domain.UserInfo;
import com.fangshuo.stars.exception.BusinessException;
import com.fangshuo.stars.exception.BusinessExceptionCode;
import com.fangshuo.stars.mapper.CircleMapper;
import com.fangshuo.stars.mapper.UserCircleMapper;
import com.fangshuo.stars.req.CircleCreateReq;
import com.fangshuo.stars.req.CircleUpdateReq;
import com.fangshuo.stars.util.CopyUtil;
import com.fangshuo.stars.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CircleService {
    private static final Logger LOG = LoggerFactory.getLogger(CircleService.class);
    @Resource
    private CircleMapper circleMapper;

    @Resource
    private UserCircleMapper userCircleMapper;

    @Resource
    private CircleBlogService circleBlogService;

    @Resource
    private SnowFlake snowFlake;

    public List<Circle> list(){
        List<Circle> circle = circleMapper.getCircleList();
        return circle;
    }

    public void createCircle(CircleCreateReq req){
        Circle circle = CopyUtil.copy(req, Circle.class);
        LOG.info("创建兴趣圈！");
        Circle circleDb = circleMapper.selectCircleByCirclename(req.getCircleName());
        if (ObjectUtils.isEmpty(circleDb)){
            Long circleId = snowFlake.nextId();
            circle.setId(circleId);
            circle.setCreatedTime(new Timestamp(new Date().getTime()));
            circleMapper.insertCircle(circle);
            // 创建兴趣圈后作为管理者加入兴趣圈
            this.joinCircle(req.getManagerId(), circleId, req.getManagerId());
        }else {
            throw new BusinessException(BusinessExceptionCode.CIRCLE_NAME_EXIST);
        }
    }


    public Circle getCircleById(Long circleId){
        Circle circle = circleMapper.getCircleById(circleId);
        Circle resp;
        if (ObjectUtils.isEmpty(circle)){
            resp = new Circle();
        }else {
            resp = CopyUtil.copy(circle, Circle.class);
        }
        return resp;
    }

    public List<User> circleMember(Long circleId){
        List<User> memberByCircleId = userCircleMapper.getMemberByCircleId(circleId);
        return memberByCircleId;
    }

    public void updateCircle(CircleUpdateReq circle){
        LOG.info("更新兴趣圈信息：{}", circle);
        circleMapper.updateCircle(circle);
    }


    public void joinCircle(Long userId, Long circleId, Long managerId){
        Circle circle = userCircleMapper.selectCircleByUserIdAndCircleId(userId, circleId);
        if (ObjectUtils.isEmpty(circle)){
            Long id = snowFlake.nextId();
            userCircleMapper.insert(id, userId, circleId, managerId);
        }else {
            throw new BusinessException(BusinessExceptionCode.JOINED_CIRCLE);
        }
    }

    public void exitCircle(Long userId, Long circleId){
        userCircleMapper.delete(userId, circleId);
    }

    public List<Circle> myJoinedCircle(Long userId){
        List<Circle> circles = userCircleMapper.selectCirclesByUserId(userId);
        return circles;
    }

    public List<Circle> myCreatedCircle(Long userId){
        List<Circle> circles = circleMapper.selectCirclesByManagerId(userId);
        return circles;
    }


    public void deleteCircle(Long circleId){
        userCircleMapper.deleteByCircleId(circleId);
        circleBlogService.deleteByCircleId(circleId);
        circleMapper.deleteCircle(circleId);
    }

    public void deleteMemberFromCircle(Long circleId, Long memberId){
        userCircleMapper.delete(memberId, circleId);
    }

}

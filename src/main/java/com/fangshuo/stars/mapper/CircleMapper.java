package com.fangshuo.stars.mapper;

import com.fangshuo.stars.domain.Circle;
import com.fangshuo.stars.domain.User;
import com.fangshuo.stars.req.CircleUpdateReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CircleMapper {
    public List<Circle> getCircleList();
    public Circle selectCircleByCirclename(String circlename);
    public Circle getCircleById(Long id);
    public void insertCircle(Circle circle);
    public void updateCircle(CircleUpdateReq circleUpdateReq);
    public void deleteCircle(Long circleId);

}

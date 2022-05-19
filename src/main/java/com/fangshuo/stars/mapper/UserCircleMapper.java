package com.fangshuo.stars.mapper;

import com.fangshuo.stars.domain.Circle;

import java.util.List;

public interface UserCircleMapper {

    public void insert(Long id, Long userId, Long circleId, Long managerId);

    public void delete(Long userId, Long circleId);

    public void deleteByCircleId(Long circleId);

    public List<Circle> selectCirclesByUserId(Long userId);

    public List<Circle> selectCirclesByManagerId(Long userId);

    public Circle selectCircleByUserIdAndCircleId(Long userId, Long circleId);

}

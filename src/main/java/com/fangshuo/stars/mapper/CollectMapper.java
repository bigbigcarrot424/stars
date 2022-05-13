package com.fangshuo.stars.mapper;

import com.fangshuo.stars.domain.Collect;
import com.fangshuo.stars.resp.CollectListByUserResp;

import java.util.List;

public interface CollectMapper {
    public List<CollectListByUserResp> getCollectListByUser(Long userId);

    public void save(Collect collect);


    public void deleteByBlogId(Long blogId);

    public void deleteByUserIdAndBlogId(Long userId, Long blogId);

    public Collect getCollectByUserIdAndBlogId(Long userId, Long blogId);
}

package com.fangshuo.stars.service;

import com.fangshuo.stars.domain.Likes;
import com.fangshuo.stars.mapper.LikesMapper;
import com.fangshuo.stars.req.LikesSaveReq;
import com.fangshuo.stars.resp.LikesListByUserResp;
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
public class LikesService {
    private static final Logger LOG = LoggerFactory.getLogger(LikesService.class);

    @Resource
    private LikesMapper likesMapper;

    @Resource
    private SnowFlake snowFlake;

    public List<LikesListByUserResp> list(Long userId){
        List<LikesListByUserResp> likesList = likesMapper.getLikesListByUser(userId);
        return likesList;
    }

    public Integer likesNum(Long userId){
        List<LikesListByUserResp> likesList = likesMapper.getLikesListByUser(userId);
        return likesList.size();
    }


    public void save(LikesSaveReq req){
        LOG.info("req:{}",req);
        Likes likes = CopyUtil.copy(req, Likes.class);

        if (ObjectUtils.isEmpty(likes.getId())){
            long likesId = snowFlake.nextId();
            likes.setId(likesId);
        }
        likes.setCommentTime(new Timestamp(new Date().getTime()));
        likesMapper.save(likes);
    }
}

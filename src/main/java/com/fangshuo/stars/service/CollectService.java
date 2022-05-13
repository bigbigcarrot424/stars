package com.fangshuo.stars.service;

import com.fangshuo.stars.domain.Collect;
import com.fangshuo.stars.exception.BusinessException;
import com.fangshuo.stars.exception.BusinessExceptionCode;
import com.fangshuo.stars.mapper.BlogMapper;
import com.fangshuo.stars.mapper.CollectMapper;
import com.fangshuo.stars.req.CollectSaveReq;
import com.fangshuo.stars.resp.BlogListResp;
import com.fangshuo.stars.resp.CollectListByUserResp;
import com.fangshuo.stars.util.CopyUtil;
import com.fangshuo.stars.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CollectService {
    private static final Logger LOG = LoggerFactory.getLogger(CollectService.class);

    @Resource
    private CollectMapper collectMapper;

    @Resource
    private BlogService blogService;

    @Resource
    private SnowFlake snowFlake;

    public List<CollectListByUserResp> list(Long userId){
        List<CollectListByUserResp> collectList = collectMapper.getCollectListByUser(userId);
        return collectList;
    }

    public void save(CollectSaveReq req){
        LOG.info("req:{}",req);
        Collect collect = CopyUtil.copy(req, Collect.class);
        Collect collectByUserAndBlog = collectMapper.getCollectByUserIdAndBlogId(req.getCollectorId(),req.getBlogId());
        if (ObjectUtils.isEmpty(collectByUserAndBlog)){
            long collectId = snowFlake.nextId();
            collect.setId(collectId);
            collect.setCollectTime(new Timestamp(new Date().getTime()));
            collectMapper.save(collect);
        }else {
            throw new BusinessException(BusinessExceptionCode.COLLECT_REPEAT);
        }
    }

    public void deleteByBlogId(Long blogId){
        LOG.info("删除帖子{}收藏",blogId);
        collectMapper.deleteByBlogId(blogId);
    }

    public void deleteByUserIdAndBlogId(Long userId, Long blogId){
        collectMapper.deleteByUserIdAndBlogId(userId, blogId);
    }

    public List<BlogListResp> collectBlogList(Long userId){
        List<CollectListByUserResp> collectListByUser = collectMapper.getCollectListByUser(userId);
        List<BlogListResp> collectBlogList = new ArrayList<>();
        for (CollectListByUserResp collectListByUserResp : collectListByUser) {
            BlogListResp blogById = blogService.getBlogById(collectListByUserResp.getBlogId());
            collectBlogList.add(blogById);
        }
        return collectBlogList;
    }
}

package com.fangshuo.stars.controller;

import com.alibaba.fastjson.JSONObject;
import com.fangshuo.stars.domain.Circle;
import com.fangshuo.stars.req.CircleCreateReq;
import com.fangshuo.stars.req.CircleUpdateReq;
import com.fangshuo.stars.resp.CommonResp;
import com.fangshuo.stars.service.CircleService;
import com.fangshuo.stars.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/circle")
public class CircleController {
    private static final Logger LOG = LoggerFactory.getLogger(CircleController.class);

    @Resource
    private CircleService circleService;

    @Resource
    private SnowFlake snowFlake;

    //查找兴趣圈列表
    @GetMapping("/list")
    public CommonResp<List<Circle>> circleList() {
        CommonResp<List<Circle>> resp = new CommonResp<>();
        resp.setContent(circleService.list());
        return resp;
    }

    //创建兴趣圈
    @PostMapping("/create")
    public CommonResp create(@Valid @RequestBody CircleCreateReq req){
        CommonResp<Object> resp = new CommonResp<>();
        circleService.createCircle(req);
        return resp;
    }

    //修改兴趣圈信息
    @PostMapping("/update")
    public CommonResp update(@Valid @RequestBody CircleUpdateReq req){
        CommonResp<Object> resp = new CommonResp<>();
        circleService.updateCircle(req);
        return resp;
    }

    //删除兴趣圈
    @GetMapping("/drop/{circleId}")
    public CommonResp drop(@PathVariable Long circleId){
        CommonResp resp = new CommonResp<>();
        circleService.deleteCircle(circleId);
        return resp;
    }
}

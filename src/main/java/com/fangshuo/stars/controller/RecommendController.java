package com.fangshuo.stars.controller;

import com.fangshuo.stars.resp.CommonResp;
import com.fangshuo.stars.resp.UserInfoResp;
import com.fangshuo.stars.service.RecommendService;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @Resource
    private RecommendService recommendService;

    //当前用户聚类信息
    @GetMapping("/clusterUser/{name}")
    public CommonResp<List<UserInfoResp>> clusterUser(@PathVariable String name){
        CommonResp resp = new CommonResp<>();
        List<UserInfoResp> userCluster = recommendService.getUserCluster(name);
        resp.setContent(userCluster);
        return resp;
    }

}

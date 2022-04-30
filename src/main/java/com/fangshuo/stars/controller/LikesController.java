package com.fangshuo.stars.controller;

import com.fangshuo.stars.req.LikesSaveReq;
import com.fangshuo.stars.resp.CommonResp;
import com.fangshuo.stars.resp.LikesListByUserResp;
import com.fangshuo.stars.service.LikesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikesController {
    @Resource
    private LikesService likesService;

    @GetMapping("/list/{userId}")
    public CommonResp<List<LikesListByUserResp>> likesList(@PathVariable Long userId) {
        CommonResp<List<LikesListByUserResp>> resp = new CommonResp<>();
        List<LikesListByUserResp> likesList = likesService.list(userId);
        resp.setContent(likesList);
        return resp;
    }

    @GetMapping("/likesNum/{userId}")
    public CommonResp likesNum(@PathVariable Long userId) {
        CommonResp resp = new CommonResp<>();
        Integer likesNum = likesService.likesNum(userId);
        resp.setContent(likesNum);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp likesSave(@Valid @RequestBody LikesSaveReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        likesService.save(req);
        return resp;
    }
}

package com.fangshuo.stars.controller;

import com.fangshuo.stars.req.CircleBlogSaveReq;
import com.fangshuo.stars.resp.BlogListResp;
import com.fangshuo.stars.resp.CommonResp;
import com.fangshuo.stars.service.BlogService;
import com.fangshuo.stars.service.CircleBlogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/circleBlog")
public class CircleBlogController {
    @Resource
    private CircleBlogService circleBlogService;

    @GetMapping("/blogListInCircleNum/{circleId}")
    public CommonResp<Integer> blogListInCircle(@PathVariable Long circleId) {
        CommonResp<Integer> resp = new CommonResp<>();
        Integer blogListInCircleNum = circleBlogService.blogListInCircleNum(circleId);
        resp.setContent(blogListInCircleNum);
        return resp;
    }

    @GetMapping("/blogListInCircleByPage/{circleId}/{pageNum}/{pageSize}")
    public CommonResp<List<BlogListResp>> blogListInCircleByPage(@PathVariable Long circleId, @PathVariable int pageNum, @PathVariable int pageSize) {
        CommonResp<List<BlogListResp>> resp = new CommonResp<>();
        List<BlogListResp> blogList = circleBlogService.blogListInCircleByPage(circleId, pageNum, pageSize);
        resp.setContent(blogList);
        return resp;
    }

    @PostMapping("/saveCircleBlog")
    public CommonResp blogSave(@Valid @RequestBody CircleBlogSaveReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        circleBlogService.saveCircleBlog(req);
        return resp;
    }

    @GetMapping("/deleteCircleBlog/{blogId}")
    public CommonResp deleteCircleBlog(@PathVariable Long blogId) {
        CommonResp<Object> resp = new CommonResp<>();
        circleBlogService.deleteById(blogId);
        return resp;
    }
}

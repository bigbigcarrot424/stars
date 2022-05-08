package com.fangshuo.stars.controller;

import com.fangshuo.stars.domain.Blog;
import com.fangshuo.stars.req.BlogEditReq;
import com.fangshuo.stars.req.BlogListByPageReq;
import com.fangshuo.stars.req.BlogSaveReq;
import com.fangshuo.stars.resp.BlogListResp;
import com.fangshuo.stars.resp.CommonResp;
import com.fangshuo.stars.service.BlogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Resource
    private BlogService blogService;

    @GetMapping("/list")
    public CommonResp<List<BlogListResp>> blogList() {
        CommonResp<List<BlogListResp>> resp = new CommonResp<>();
        List<BlogListResp> blogList = blogService.list();
        resp.setContent(blogList);
        return resp;
    }

    @GetMapping("/blogNum")
    public CommonResp blogNum() {
        CommonResp resp = new CommonResp<>();
        Integer blogNum = blogService.blogNum();
        resp.setContent(blogNum);
        return resp;
    }

    @GetMapping("/myBlogNum/{userId}")
    public CommonResp myBlogNum(@PathVariable Long userId) {
        CommonResp resp = new CommonResp<>();
        Integer blogNum = blogService.myBlogNum(userId);
        resp.setContent(blogNum);
        return resp;
    }

    @GetMapping("/listByPage/{pageNum}/{pageSize}")
    public CommonResp<List<BlogListResp>> blogListByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        CommonResp<List<BlogListResp>> resp = new CommonResp<>();
        List<BlogListResp> blogList = blogService.getBlogListByPage(pageNum, pageSize);
        resp.setContent(blogList);
        return resp;
    }

    @GetMapping("/myListByPage/{pageNum}/{pageSize}/{userId}")
    public CommonResp<List<BlogListResp>> myBlogListByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @PathVariable Long userId) {
        CommonResp<List<BlogListResp>> resp = new CommonResp<>();
        List<BlogListResp> blogList = blogService.getMyBlogListByPage(pageNum, pageSize, userId);
        resp.setContent(blogList);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp blogSave(@Valid @RequestBody BlogSaveReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        blogService.save(req);
        return resp;
    }

    @PostMapping("/edit")
    public CommonResp blogSave(@Valid @RequestBody BlogEditReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        blogService.edit(req);
        return resp;
    }
}

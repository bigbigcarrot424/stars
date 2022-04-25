package com.fangshuo.stars.controller;

import com.fangshuo.stars.domain.Blog;
import com.fangshuo.stars.req.BlogSaveReq;
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
    public List<Blog> blogList() {
        return blogService.list();
    }

    @PostMapping("/save")
    public CommonResp blogSave(@Valid @RequestBody BlogSaveReq req) {
        CommonResp<Object> resp = new CommonResp<>();
        blogService.save(req);
        return resp;
    }
}

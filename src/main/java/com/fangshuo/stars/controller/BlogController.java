package com.fangshuo.stars.controller;

import com.fangshuo.stars.domain.Blog;
import com.fangshuo.stars.service.BlogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BlogController {
    @Resource
    private BlogService blogService;

    @GetMapping("/blog/list")
    public List<Blog> blogList() {
        return blogService.list();
    }
}

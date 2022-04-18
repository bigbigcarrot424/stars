package com.fangshuo.stars.controller;

import com.fangshuo.stars.domain.Test;
import com.fangshuo.stars.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {
    @Resource
    private TestService testService;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/test/list")
    public List<Test> testList() {
        return testService.list();
    }
}

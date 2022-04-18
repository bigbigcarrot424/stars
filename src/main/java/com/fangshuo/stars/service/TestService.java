package com.fangshuo.stars.service;

import com.fangshuo.stars.domain.Test;
import com.fangshuo.stars.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {
    @Resource
    private TestMapper testMapper;

    public List<Test> list(){
        List<Test> test = testMapper.getTest();
        return test;
    }
}

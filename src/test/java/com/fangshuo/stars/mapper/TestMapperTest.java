package com.fangshuo.stars.mapper;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
@SpringBootTest
public class TestMapperTest {
    @Resource
    private TestMapper testMapper;

    @Test
    public void testMapper(){
        List<com.fangshuo.stars.domain.Test> test = testMapper.getTest();
        System.out.println(test);
    }
}

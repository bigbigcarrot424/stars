package com.fangshuo.stars;

import com.fangshuo.stars.mapper.TestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class StarsApplicationTests {
    @Resource
    private TestMapper testMapper;

    @Test
    void contextLoads() {
        List<com.fangshuo.stars.domain.Test> test = testMapper.getTest();
        System.out.println(test);
    }


}

package com.fangshuo.stars;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.fangshuo")
@SpringBootApplication
@MapperScan("com.fangshuo.stars.mapper")
public class StarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarsApplication.class, args);
    }

}

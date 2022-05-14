package com.fangshuo.stars;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;


@ComponentScan("com.fangshuo")
@SpringBootApplication
@MapperScan("com.fangshuo.stars.mapper")
@EnableScheduling
public class StarsApplication {
    private static final Logger LOG = LoggerFactory.getLogger(StarsApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(StarsApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！");
        LOG.info("地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }
}

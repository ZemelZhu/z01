package com.zemel.mallserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.zemel.mallserver.mapper")
@ComponentScan("com.zemel.*")
@EnableCaching
public class MallserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallserverApplication.class, args);
    }

}

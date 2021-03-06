package com.zemel.web2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.zemel.*.mapper")
@ComponentScan("com.zemel.*")
@EnableCaching
@EnableSwagger2
public class Web2Application {

    public static void main(String[] args) {
        SpringApplication.run(Web2Application.class, args);
    }

}

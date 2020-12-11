package com.zemel.gameserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@MapperScan("com.zemel.*.mapping")
@ComponentScan("com.zemel.*")
@EnableCaching
public class GameserverApplication implements CommandLineRunner  {

    public static void main(String[] args) {
       SpringApplication.run(GameserverApplication.class, args);
//        ComponentManager.getInstance().start();
//        new SpringApplicationBuilder(GameserverApplication.class)
//                .web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
//                .bannerMode(Banner.Mode.OFF).run(args);
       // SpringApplication.run(GameserverApplication.class, args).web(WebApplicationType.NONE);
    }





    @Override
    public void run(String... args) throws Exception {

    }
}

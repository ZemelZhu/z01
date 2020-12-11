package com.zemel.mallserver.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zemel
 * @Date: 2020/4/5 0:41
 */
@Configuration
@Data
public class WeChatLoginConfig {
    @Value("${WeChatLoginConfig.loginUrl}")
    private String loginUrl;
    @Value("${WeChatLoginConfig.appId}")
    private String appId;
    @Value("${WeChatLoginConfig.secret}")
    private String secret;
}

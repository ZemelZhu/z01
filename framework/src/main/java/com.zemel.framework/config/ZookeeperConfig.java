package com.zemel.framework.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zemel
 * @Date: 2020/3/3 23:10
 */
@Configuration
@Data
public class ZookeeperConfig {
    @Value("${zookeeper.address:127.0.0.1}")
    private String connectString;
    @Value("${zookeeper.timeout:0}")
    private int timeout;
}

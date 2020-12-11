package com.zemel.framework.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: zemel
 * @Date: 2020/1/20 22:46
 */
@Component
@Data
public class ServerConfig {
    @Value("${server.workerId}")
    private int workerId;
    @Value("${server.decenterId}")
    private int decenterId;
    @Value("${server.dev}")
    private boolean dev;
    @Value("${server.host}")
    private String host;
    @Value("${server.name}")
    private String name;
}

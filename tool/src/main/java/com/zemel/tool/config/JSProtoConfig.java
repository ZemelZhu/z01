package com.zemel.tool.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: zemel
 * @Date: 2020/2/9 20:26
 */
@Component
@Data
public class JSProtoConfig {
    @Value("${JSProtoConfig.outPutPath}")
    private String outPutPath;
}

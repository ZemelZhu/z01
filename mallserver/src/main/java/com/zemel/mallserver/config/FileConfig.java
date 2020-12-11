package com.zemel.mallserver.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zemel
 * @Date: 2020/3/29 13:01
 */
@Configuration
@Data
public class FileConfig {
    @Value("${FileConfig.fileDir}")
    private String fileDir;
    @Value("${FileConfig.fileDownLoad}")
    private String fileDownLoad;
}

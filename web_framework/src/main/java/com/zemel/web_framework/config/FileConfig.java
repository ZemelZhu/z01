package com.zemel.web_framework.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zemel
 * @Date: 2020/7/18 12:34
 */
@Configuration
@Data
public class FileConfig {
    @Value("${FileConfig.fileDir}")
    private String fileDir;
    @Value("${FileConfig.fileDownLoad}")
    private String fileDownLoad;
    @Value("${FileConfig.resourcesPath}")
    private String resourcesPath;
}

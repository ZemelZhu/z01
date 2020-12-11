package com.zemel.tool.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author: zemel
 * @Date: 2020/1/16 20:35
 */
@Component
@Data
public class GenerateConfig {
    @Value("${GenerateConfig.packageName}")
    private String packageName;
    @Value("${GenerateConfig.isLomBok}")
    private boolean isLombok;
    @Value("${GenerateConfig.generate}")
    private boolean generate;
    @Value("#{${GenerateConfig.prefix}}")
    private Map<String,String> prefix;
    @Value("#{${GenerateConfig.commonPrefix}}")
    private Map<String,String> commonPrefix;
    @Value("#{'${GenerateConfig.databasesList}'.split(',')}")
    private List<String> databases;
}

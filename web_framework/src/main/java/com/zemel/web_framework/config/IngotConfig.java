package com.zemel.web_framework.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/7/18 16:59
 */
@Data
@Configuration
public class IngotConfig {
    @Value("#{'${IngotConfig.ingot}'.split(',')}")
    private List<String> ingot;
}

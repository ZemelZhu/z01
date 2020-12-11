package com.zemel.framework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @Author: zemel
 * @Date: 2020/5/5 21:43
 */
@Data
@AllArgsConstructor
public class LoginErrorDto {
    private String ip;
    private int errorAllCount;
    private Map<String, Integer> roleCount;
}

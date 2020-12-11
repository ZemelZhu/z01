package com.zemel.mallserver.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zemel
 * @Date: 2020/4/4 23:44
 */
@Builder
@Data
public class AdminLoginVo {
    private int id;
    private String token;
    private Date time;
}

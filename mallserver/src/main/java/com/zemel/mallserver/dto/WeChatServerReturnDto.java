package com.zemel.mallserver.dto;

import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/4/4 14:14
 */
@Data
public class WeChatServerReturnDto {
    private String openid;
    private String session_key;
}

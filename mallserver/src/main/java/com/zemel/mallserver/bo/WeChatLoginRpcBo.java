package com.zemel.mallserver.bo;

import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/4/5 0:19
 */
@Data
public class WeChatLoginRpcBo {
    private String openid;
    private String session_key;
}

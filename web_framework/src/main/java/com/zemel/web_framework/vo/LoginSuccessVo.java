package com.zemel.web_framework.vo;

import com.zemel.data.entiy.UserInfo;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/7/17 23:06
 */
@Data
public class LoginSuccessVo implements BaseVo{
    private int id;
    private String token;
    private String userName;
    public LoginSuccessVo( String token)
    {
        this.token =token;
    }

    public LoginSuccessVo(String token, UserInfo userInfo) {
        this(token);
        this.id = userInfo.getId();
        this.userName = userInfo.getUserName();
    }

}

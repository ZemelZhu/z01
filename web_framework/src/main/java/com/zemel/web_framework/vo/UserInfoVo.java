package com.zemel.web_framework.vo;

import com.zemel.data.entiy.UserInfo;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/7/18 22:14
 */
@Data
public class UserInfoVo implements BaseVo{
    private int id;
    private String userName;
    public UserInfoVo()
    {

    }
    public UserInfoVo(UserInfo playerInfo) {
        this.id = playerInfo.getId();
        this.userName = playerInfo.getUserName();
    }
}

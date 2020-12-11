package com.zemel.web_framework.component;

import com.zemel.data.entiy.UserInfo;
import com.zemel.framework.component.IComponent;
import com.zemel.web_framework.mapper.UserInfoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: zemel
 * @Date: 2020/8/1 13:31
 */
@Component
public class LoginComponent implements IComponent {
    @Resource
    private UserInfoMapper userInfoMapper;
    public int findUserIdByToken(String token)
    {
        UserInfo userInfoByToken = userInfoMapper.getUserInfoByToken(token);
        if(userInfoByToken!=null)
            return userInfoByToken.getId();
        return 0;
    }
    @Override
    public boolean initialize() {
        return true;
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean reload() {
        return true;
    }
}

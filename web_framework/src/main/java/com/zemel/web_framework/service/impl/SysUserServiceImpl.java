package com.zemel.web_framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zemel.data.entiy.UserInfo;
import com.zemel.data.type.QueryWrapperUtil;
import com.zemel.web_framework.mapper.UserInfoMapper;
import com.zemel.web_framework.model.User;
import com.zemel.web_framework.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service
public class SysUserServiceImpl implements UserService {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Override
    public User findByUsername(String username) {
//        User user = new User();
//        user.setId(1L);
//        user.setUsername(username);
//        String password = new BCryptPasswordEncoder().encode("123");
//        user.setPassword(password);
        QueryWrapper<UserInfo> playerInfoQueryWrapper = QueryWrapperUtil.buildDataWrapper(UserInfo.class);
        playerInfoQueryWrapper.eq("user_name",username);
        UserInfo playerInfo = userInfoMapper.selectOne(playerInfoQueryWrapper);
        if(playerInfo!=null)
        {
            return new User(playerInfo.getId(), username, playerInfo.getPassword());
        }
        return null;
    }

    @Override
    public Set<String> findPermissions(String username) {
        Set<String> permissions = new HashSet<>();
        QueryWrapper<UserInfo> playerInfoQueryWrapper = QueryWrapperUtil.buildDataWrapper(UserInfo.class);
        playerInfoQueryWrapper.eq("user_name",username);
        UserInfo playerInfo = userInfoMapper.selectOne(playerInfoQueryWrapper);
        permissions.add("sys:user:view");
        permissions.add("sys:user:add");
        permissions.add("sys:user:edit");
        permissions.add("sys:user:delete");
        return permissions;
    }

}

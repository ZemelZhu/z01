package com.zemel.web_framework.controller;

import com.zemel.data.entiy.UserInfo;
import com.zemel.data.type.DataStatus;
import com.zemel.framework.exception.TipException;
import com.zemel.framework.type.LogConfig;
import com.zemel.framework.until.StringUtil;
import com.zemel.framework.vo.ResponseVo;
import com.zemel.web_framework.mapper.UserInfoMapper;
import com.zemel.web_framework.model.LoginBean;
import com.zemel.web_framework.security.JwtAuthenticatioToken;
import com.zemel.web_framework.type.UserType;
import com.zemel.web_framework.utils.SecurityUtils;
import com.zemel.web_framework.vo.LoginSuccessVo;
import com.zemel.web_framework.vo.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * 登录控制器
 *
 * @author Louis
 * @date Nov 28, 2018
 */
@RestController
@Api(tags = "登录相关接口")
public class LoginController {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(LogConfig.LOGIN);
    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "123"),
            @ApiImplicitParam(name = "password", value = "密码", defaultValue = "1234"),
    })
    @PostMapping(value = "/login")
    public ResponseVo login(LoginBean loginBean, HttpServletRequest request) throws IOException {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        // 系统登录认证
        try {
            JwtAuthenticatioToken token = SecurityUtils.login(request, username, password, authenticationManager);
            UserInfo userInfo = userInfoMapper.getUserInfoByName(username);
            userInfo.setToken(token.getToken());
            userInfo.setLoginTime(new Date());
            userInfo.setUpdateTime(new Date());
            LoginSuccessVo loginSuccessVo = new LoginSuccessVo(token.getToken(),userInfo);
            userInfoMapper.updateById(userInfo);
            LOGGER.error(loginBean+"");
            return ResponseVo.build(loginSuccessVo);
        } catch (Exception e) {
            return ResponseVo.buildFail(e.getMessage());
        }
    }

    @ApiOperation("注册")
    @PostMapping(value = "/register")
    public boolean register(@RequestBody LoginBean loginBean) throws IOException {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        loginBean.check();
        synchronized (this) {
//            QueryWrapper<UserInfo> playerInfoQueryWrapper = QueryWrapperUtil.buildDataWrapper(UserInfo.class);
//            playerInfoQueryWrapper.eq("user_name",username);
            UserInfo playerInfo = userInfoMapper.getUserInfoByName(username);
            if (playerInfo != null)
                throw new TipException("该账号已经被注册");
            playerInfo = new UserInfo();
            playerInfo.setUserName(username);
            String encode = new BCryptPasswordEncoder().encode(password);
            playerInfo.setPassword(encode);
            Date date = new Date();
            playerInfo.setCreateTime(date);
            playerInfo.setUpdateTime(date);
            playerInfo.setLoginTime(date);
            playerInfo.setStatus(DataStatus.NORMAL.ordinal());
            playerInfo.setUserStatus(0);
            playerInfo.setUserType(UserType.USER.ordinal());
            if (userInfoMapper.insert(playerInfo) > 0)
                return true;
        }
        return false;
    }

    @ApiOperation("注销登录")
    @GetMapping(value = "/logout")
    public boolean logout() throws IOException {
        String username = SecurityUtils.getUsername();
        return true;
    }
    @ApiOperation("修改密码")
    @GetMapping(value = "/modifyPassword")
    public boolean modifyPassword(String password,HttpServletRequest request) throws IOException {
        String username = SecurityUtils.getUsername();
        UserInfo playerInfo = userInfoMapper.getUserInfoByName(username);
        if (playerInfo == null)
            throw new TipException("未登录");
        if (StringUtil.isNullOrEmpty(password))
            throw new TipException("密码不能为空");
        if (password.length() < 3)
            throw new TipException("密码不能太短");
        String encode = new BCryptPasswordEncoder().encode(password);
        playerInfo.setPassword(encode);
        playerInfo.setUpdateTime(new Date());
        userInfoMapper.updateById(playerInfo);
        //JwtTokenUtils.deleteToken(request);
        return true;
    }
    @ApiOperation("用户信息")
    @GetMapping(value = "/info")
    public UserInfoVo info()
    {
        String username = SecurityUtils.getUsername();
        UserInfo playerInfo = userInfoMapper.getUserInfoByName(username);
        if(playerInfo==null)
            return new UserInfoVo();
        UserInfoVo userInfoVo = new UserInfoVo(playerInfo);
        return userInfoVo;
    }
}

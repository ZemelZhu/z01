package com.zemel.mallserver.services.admin;

import com.zemel.data.type.DataStatus;
import com.zemel.data.type.Roles;
import com.zemel.framework.exception.TipException;
import com.zemel.framework.login.RolesLoginService;
import com.zemel.framework.until.*;
import com.zemel.mallserver.entiy.AdminUserInfo;
import com.zemel.mallserver.mapper.AdminUserInfoMapper;
import com.zemel.mallserver.vo.AdminLoginVo;
import com.zemel.mallserver.vo.AdminUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author: zemel
 * @Date: 2020/3/21 15:54
 */
@Service
public class AdminUserService {
    @Resource
    private AdminUserInfoMapper adminUserInfoMapper;
    @Autowired
    private RolesLoginService rolesLoginService;
    public AdminLoginVo login(String username, String password, HttpServletRequest request) {
        String requestIP = ServletUtil.getRequestIP(request);
        rolesLoginService.checkLogin(requestIP,Roles.ADMIN.getSecret());
        AdminUserInfo adminUserInfo = adminUserInfoMapper.selectByName(username);
        if(adminUserInfo==null)
            throw new TipException("找不到账号");
        password = EncryptUtil.getMD5LowerCase(password);
        if ( adminUserInfo.getPassword().equals(password)) {
            String sign = TokenUtil.sign(adminUserInfo.getId(), Roles.ADMIN);
            AdminLoginVo adminLoginVo = AdminLoginVo.builder().id(adminUserInfo.getId()).token(sign).time(new Date()).build();
            RequestUtil.loginLog("requestIp:"+requestIP+"userInfo:"+adminLoginVo+"password:"+password);
            return adminLoginVo;
        }
        RequestUtil.loginLog("requestIp:"+requestIP+"password:"+password+" info"+adminUserInfo);
        rolesLoginService.loginWithPasswordError(requestIP,Roles.ADMIN.getSecret());
        return null;
    }

    public AdminUserInfoVo getInfo(int userId) {
        AdminUserInfo adminUserInfo = adminUserInfoMapper.selectById(userId);
        if (adminUserInfo != null) {
            AdminUserInfoVo adminUserInfoVo = AdminUserInfoVo.buildInfo(adminUserInfo);
            return adminUserInfoVo;
        }
        return null;
    }

    public void modifyPassword(int userId, String password) {
        if(StringUtil.isNullOrEmpty(password))
            throw new TipException("密码不能为空");
        if(password.length()<6)
            throw new TipException("密码必须大于6位");
        if(password.length()>20)
            throw new TipException("密码不能大于20位");
        AdminUserInfo adminUserInfo = adminUserInfoMapper.selectById(userId);
        if(adminUserInfo==null||adminUserInfo.getStatus()!= DataStatus.NORMAL.ordinal())
            throw new TipException("账号异常");
        String md5LowerCase = EncryptUtil.getMD5LowerCase(password);
        adminUserInfo.setPassword(md5LowerCase);
        adminUserInfoMapper.updateById(adminUserInfo);
        RequestUtil.loginLog("modify password:"+password);
    }
}

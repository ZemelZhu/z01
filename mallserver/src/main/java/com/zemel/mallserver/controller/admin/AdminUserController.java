package com.zemel.mallserver.controller.admin;

import com.zemel.data.type.Roles;
import com.zemel.framework.annotation.RolesAnnotation;
import com.zemel.framework.until.TokenUtil;
import com.zemel.framework.vo.ResponseVo;
import com.zemel.mallserver.services.admin.AdminUserService;
import com.zemel.mallserver.services.mall.MallUserService;
import com.zemel.mallserver.vo.AdminLoginVo;
import com.zemel.mallserver.vo.AdminUserInfoVo;
import com.zemel.mallserver.vo.MallUserInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zemel
 * @Date: 2020/3/21 13:11
 */
@RolesAnnotation(roles = Roles.ADMIN)
@RestController
@CrossOrigin
@RequestMapping("admin/user")
public class AdminUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserController.class);
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private MallUserService mallUserService;
    @PostMapping("login")
    public AdminLoginVo login(String username, String password, HttpServletRequest request) {
        AdminLoginVo login = adminUserService.login(username, password, request);
        return login;
    }

    @PostMapping("logout")
    public ResponseVo logOut() {
        return ResponseVo.build(true);
    }

    @GetMapping("info")
    public AdminUserInfoVo getInfo(HttpServletRequest request) {
        int userId = TokenUtil.getUserId(request,Roles.ADMIN);
        AdminUserInfoVo info = adminUserService.getInfo(userId);
        return info;
    }
    @GetMapping("modifyPassword")
    public boolean modifyPassword(HttpServletRequest request,String password)
    {
        int userId = TokenUtil.getUserId(request);
        adminUserService.modifyPassword(userId,password);
        return true;
    }
    @GetMapping("getUserToken")
    public ResponseVo getUserToken(HttpServletRequest request)
    {
        int userId = TokenUtil.getUserId(request);
        String sign = TokenUtil.sign(userId);
        return ResponseVo.build(sign);
    }

    @RequestMapping("getUserInfoById")
    public MallUserInfoVo getInfoById(int id)
    {
        return mallUserService.getInfoById(id);
    }
}

package com.zemel.web_framework.handler;

import com.zemel.framework.until.JsonUntil;
import com.zemel.framework.vo.ResponseVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zemel 处理退出成功
 * @Date: 2020/7/17 20:35
 */
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.getWriter().write(JsonUntil.objectToString(ResponseVo.build("登出成功")));
    }
}

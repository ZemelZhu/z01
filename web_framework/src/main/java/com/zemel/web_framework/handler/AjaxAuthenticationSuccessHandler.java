package com.zemel.web_framework.handler;

import com.zemel.framework.until.JsonUntil;
import com.zemel.framework.vo.ResponseVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zemel 用户登录成功时返回给前端的数据
 * @Date: 2020/7/17 20:27
 */
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.getWriter().write(JsonUntil.objectToString(ResponseVo.build("登录成功")));
    }
}

package com.zemel.web_framework.handler;

import com.zemel.framework.until.JsonUntil;
import com.zemel.framework.vo.ResponseVo;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zemel 用户登录失败返回结果
 * @Date: 2020/7/17 20:28
 */
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.getWriter().write(JsonUntil.objectToString(ResponseVo.buildFail("登录失败")));
    }
}

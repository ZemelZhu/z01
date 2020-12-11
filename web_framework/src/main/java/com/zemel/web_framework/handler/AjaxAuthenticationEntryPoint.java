package com.zemel.web_framework.handler;

import com.zemel.framework.until.JsonUntil;
import com.zemel.framework.vo.ResponseVo;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zemel 用户未登录时返回给前端的数据
 * @Date: 2020/7/17 20:21
 */
public class AjaxAuthenticationEntryPoint  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.getWriter().write(JsonUntil.objectToString(ResponseVo.buildFail("请先登录")));
    }
}

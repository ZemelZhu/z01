package com.zemel.framework.interceptor;

import com.zemel.framework.annotation.RolesAnnotation;
import com.zemel.framework.login.RolesLoginService;
import com.zemel.framework.until.JsonUntil;
import com.zemel.framework.until.RequestUtil;
import com.zemel.framework.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: zemel
 * @Date: 2020/4/1 23:30
 */
@Component
public class Interceptor implements HandlerInterceptor {
    @Autowired
    private RolesLoginService rolesLoginService;
    private static final Logger LOGGER = LoggerFactory.getLogger(Interceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Methods", request.getMethod());
            response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
        String requestURI = request.getRequestURI();
        RolesAnnotation annotation = null;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> beanType = handlerMethod.getBeanType();
            annotation = beanType.getAnnotation(RolesAnnotation.class);
        } else {
            LOGGER.error(requestURI);
        }
        String token = RequestUtil.getToken(request);
        ResponseVo verity = rolesLoginService.verity(token, annotation);
        if (verity != null) {
            returnJson(response, verity);
            return false;
        }
        return true;
    }


    private void returnJson(HttpServletResponse response, ResponseVo vo) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JsonUntil.objectToString(vo));
        } catch (IOException e) {
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}

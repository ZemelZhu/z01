package com.zemel.web_framework.config;

import com.zemel.framework.until.ClassUtil;
import com.zemel.framework.vo.ResponseVo;
import com.zemel.web_framework.vo.BaseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author: zemel
 * @Date: 2020/7/16 23:55
 */
@ControllerAdvice
public class MyResponseAdvice implements ResponseBodyAdvice<Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyResponseAdvice.class);
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//        LOGGER.error(body+"");
        if(body==null)
            return null;
        if(ClassUtil.isBaseType(body.getClass())||body instanceof BaseVo) //发生异常之后，异常处理器里面返回的已经是Result了
            body= ResponseVo.build(body);
        return body;
    }

}

package com.zemel.mallserver.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemel.framework.until.RequestUtil;
import com.zemel.framework.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: zemel
 * @Date: 2020/5/1 22:07
 */
@ControllerAdvice
public class MyResponseAdvice implements ResponseBodyAdvice<Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyResponseAdvice.class);
    @Autowired
    private ObjectMapper objectMapper;
    private static Set<String> mappering = new HashSet<>();
    static {
        mappering.add("fileUpload");
        mappering.add("mallSystem");
    }
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }
    private Object ignot(String path,Object body)
    {
        String[] split = path.split("/");
        if(split.length>2)
        {
            if(mappering.contains(split[2]))
                return body;
        }
        return null;
    }
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String path = request.getURI().getPath();
        if(ignot(path,body)==null)
        {
            if(!(body instanceof ResponseVo)) //发生异常之后，异常处理器里面返回的已经是Result了
                body= ResponseVo.build(body);
        }
//        String log = path+"|"+body;
        String log = path+"|";
        LOGGER.error(log);
        RequestUtil.log(log);
        return body;
        }

}

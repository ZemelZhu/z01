package com.zemel.mallserver.config;

import com.zemel.framework.interceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: zemel
 * @Date: 2020/4/2 20:50
 */
@Configuration
public class MvcInterceptorConfig implements WebMvcConfigurer {
    @Value("${isNotIntercept}")
    private boolean isNotIntercept;
    @Autowired
    private Interceptor interceptor;
    String[] excludePathPatterns = {"/index.html", "/favicon.ico", "/", "/static/**", "/image/**", "/error/**"};
    String[] excludePathPatternsInteface = {"/fileUpload/**", "/admin/user/login", "/sdk/weChat/**", "/common/**","/mallSystem/**"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (!isNotIntercept) {

            InterceptorRegistration addInterceptor = registry.addInterceptor(interceptor);

            // 拦截配置
            addInterceptor.addPathPatterns("/**").excludePathPatterns(excludePathPatterns).excludePathPatterns(excludePathPatternsInteface);
        }
    }
}

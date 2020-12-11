package com.zemel.web_framework.config;

import com.zemel.framework.component.LogComponent;
import com.zemel.framework.config.ServerConfig;
import com.zemel.web_framework.bean.RequestInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: zemel
 * @Date: 2020/7/16 23:20
 */
@Component
@Aspect
public class LogServiceAspect {
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private LogComponent logComponent;
    @Pointcut("execution(* com.zemel..controller..*(..))")
    public void serviceLog() {
    }

    @Around("serviceLog()")
    public Object serviceLogAround(ProceedingJoinPoint point) throws Throwable {
        try {
            long start = System.currentTimeMillis();
            Object result = point.proceed();
            RequestInfo requestInfo = new RequestInfo(start, point, result,serverConfig.isDev());
            if(logComponent!=null)
                logComponent.addLog(requestInfo);
            return result;
        } catch (Throwable e) {
            //e.printStackTrace();
//            return ResponseVo.buildException("系统开小差了");
            throw e;
        }
    }

}
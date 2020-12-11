package com.zemel.web_framework.bean;

import com.zemel.framework.annotation.ILog;
import com.zemel.framework.until.ClassUtil;
import com.zemel.framework.until.JsonUntil;
import com.zemel.framework.vo.ResponseVo;
import com.zemel.web_framework.config.LogServiceAspect;
import com.zemel.web_framework.utils.SecurityUtils;
import com.zemel.web_framework.vo.BaseVo;
import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zemel
 * @Date: 2020/8/5 19:50
 */
@Data
public class RequestInfo implements ILog {
    private static final Logger LOGGER = LoggerFactory.getLogger("request.log");
    private static final Logger LOG = LoggerFactory.getLogger(LogServiceAspect.class);
    private int userId;
    private long requestTime;
    private long runTime;
    private String requestClass;
    private String requestMethod;
    private Object[] requestArgs;
    private Object response;
    private boolean devLog;

    public RequestInfo(long start, ProceedingJoinPoint point, Object result, boolean isDev) {
        userId = SecurityUtils.getUserId0();
        this.requestTime = start;
        this.requestTime = System.currentTimeMillis() - start;
        this.requestClass = point.getTarget().getClass().getSimpleName();
        this.requestMethod = point.getSignature().getName();
        this.requestArgs = point.getArgs();
        this.response = result;
        this.devLog = isDev;
    }

    private String processResponse() {
        String message;
        if (response instanceof ResponseVo) {
            ResponseVo vo = (ResponseVo) response;
            Object data = vo.getData();
            if (data != null) {
                if (data instanceof BaseVo)
                    message = data.toString();
                else
                    message = JsonUntil.objectToString(vo.getData());
            } else
                message = vo.getMessage() + "|" + vo.getCode();
        } else {
            message = JsonUntil.objectToString(response);
        }
        return message;
    }

    private String handlerParameter() {
        StringBuilder stringBuilder = new StringBuilder();
        if (requestArgs != null) {
            for (Object pojo : requestArgs) {
                try {

                    if (pojo instanceof HttpServletRequest)
                        continue;
                    if (ClassUtil.isBaseType(pojo.getClass()))
                        stringBuilder.append(pojo).append(",");
                    else
                        stringBuilder.append(JsonUntil.objectToString(pojo));
                } catch (Exception e) {
                    stringBuilder.append(e.getMessage()).append(",");
                }
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void writeLog() {
        String log = getLog();
        if (devLog)
            LOG.error(log);
        else
            LOGGER.error(log);
    }

    @Override
    public String getLog() {
        StringBuilder message = new StringBuilder();
        message.append(userId)
                .append(STAT).append(requestClass).append(STAT)
                .append(requestMethod).append(STAT).append(handlerParameter()).append(STAT)
                .append(processResponse());
        if(runTime>1000)
            message.append(STAT).append(runTime);
        return message.toString();
    }
}

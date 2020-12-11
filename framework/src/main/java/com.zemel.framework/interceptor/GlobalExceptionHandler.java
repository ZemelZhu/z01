package com.zemel.framework.interceptor;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.exception.TipException;
import com.zemel.framework.sdk.WeChatNotifySdk;
import com.zemel.framework.type.LogConfig;
import com.zemel.framework.until.StackMessagePrint;
import com.zemel.framework.vo.ResponseVo;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @Author: zemel
 * @Date: 2020/5/1 14:17
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogConfig.EXCEPTION);
    private static final Logger SYSTEM = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private WeChatNotifySdk weChatNotifySdk;

    /**
     * 处理BadRequestParameterException异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception e) {
        boolean report = true;
        if (e instanceof TipException) {
            TipException tipException = (TipException) e;
            if (tipException.isRecord()) {
                log(false, e);
            }
            return ResponseVo.buildExceptionTip(e.getMessage());
        } else if (e instanceof MyBatisSystemException) {
            return ResponseVo.build(null);
        }
        if (e instanceof MethodArgumentTypeMismatchException) {
            report = false;
        }
        log(report, e);
        return ResponseVo.buildException(e.toString());
    }

    private void log(boolean report, Exception e) {
        String s = StackMessagePrint.printErrorTrace(e);
        if (ComponentManager.getInstance().isDev())
            SYSTEM.error("Exception" + s);
        else {
            if (report)
                ComponentManager.getInstance().getCommonThreadPool().submit(() -> {
                    ComponentManager.getInstance().serverMessage(s);
                });
            LOGGER.error("Exception" + s);
        }
    }
}

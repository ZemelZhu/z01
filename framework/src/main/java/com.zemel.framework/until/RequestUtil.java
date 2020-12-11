package com.zemel.framework.until;

import com.zemel.framework.type.LogConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zemel
 * @Date: 2020/4/3 21:10
 */
public class RequestUtil {
    private static final String[] TOKEN = {"X-Token", "token", "we_chat_token"};
    private static final Logger LOGGER = LoggerFactory.getLogger(LogConfig.REQUEST);
    private static final Logger LOGIN_LOGGER = LoggerFactory.getLogger(LogConfig.LOGIN);

    public static void log(String log) {
        LOGGER.error(log);
    }

    public static void loginLog(String log) {
        LOGIN_LOGGER.error(log);
    }

    public static String getToken(HttpServletRequest request) {
        String token = null;
        try {
            for (int i = 0; i < TOKEN.length; i++) {
                token = request.getHeader(TOKEN[i]);
                if (!StringUtil.isNullOrEmpty(token))
                    return token;
                token = (String) request.getAttribute(TOKEN[i]);
                if (!StringUtil.isNullOrEmpty(token))
                    return token;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
}

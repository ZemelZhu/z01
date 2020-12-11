package com.zemel.framework.until;

import com.zemel.framework.component.IComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zemel
 * @Date: 2020/1/16 19:07
 */
public class CommonLogUtil {
    static final Logger LOGGER = LoggerFactory.getLogger(CommonLogUtil.class);

    public static void log(String log) {
        LOGGER.error(log);
    }
}

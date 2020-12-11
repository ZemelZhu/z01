package com.zemel.framework.component;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.annotation.ILog;
import org.springframework.stereotype.Component;

/**
 * @Author: zemel
 * @Date: 2020/8/5 20:08
 */
@Component
public class LogComponent implements IComponent {
    @Override
    public boolean initialize() {
        return true;
    }

    public void addLog(ILog log) {
        ComponentManager.getInstance().getCommonThreadPool().submit(() -> {
            log.writeLog();
        });
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean reload() {
        return true;
    }
}

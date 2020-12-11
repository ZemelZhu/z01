package com.zemel.framework.component;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: zemel
 * @Date: 2020/3/11 22:43
 */
public class IMqSenderComponent implements IComponent {
    @Override
    public boolean initialize() {
        return true;
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

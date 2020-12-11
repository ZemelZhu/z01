package com.zemel.framework.component;

import org.springframework.stereotype.Component;

/**
 * @Author: zemel
 * @Date: 2020/3/31 22:08
 */
@Component
public abstract class IdComponent implements IComponent {
    @Override
    public boolean initialize() {
        return false;
    }


}

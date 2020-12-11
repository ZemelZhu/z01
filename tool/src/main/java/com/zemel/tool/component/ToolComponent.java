package com.zemel.tool.component;

import com.zemel.framework.component.IComponent;

/**
 * @Author: zemel
 * @Date: 2020/8/1 10:58
 */
public abstract class ToolComponent implements IComponent {
    @Override
    public boolean initialize() {
        return true;
    }

    @Override
    public boolean start() {
        if (init()) {
            return init0();
        }
        return true;
    }

    protected abstract boolean init();

    protected abstract boolean init0();

    @Override
    public void stop() {

    }

    @Override
    public boolean reload() {
        return true;
    }
}

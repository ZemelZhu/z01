package com.zemel.framework.component;

import com.zemel.framework.socket.netty.net.AbstractNettyAdapter;

/**
 * @Author: zemel
 * @Date: 2020/1/19 23:14
 */
public abstract class BridgeServerComponent implements IComponent {
    protected AbstractNettyAdapter nettyAdapter;

    @Override
    public boolean start() {
        return nettyAdapter.start();
    }

    @Override
    public void stop() {
        nettyAdapter.stop();
    }


    @Override
    public boolean reload() {
        nettyAdapter.stop();
        return nettyAdapter.start();
    }
}

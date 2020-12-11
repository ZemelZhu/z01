package com.zemel.framework.socket.netty.client;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.component.ClientConnectComponent;
import com.zemel.framework.socket.netty.IMessageHandler;
import io.netty.channel.Channel;

/**
 * netty 客户端链接对象(支持多种协议,处理器)
 *
 * @author zemel
 */
public class NettyClientConnection extends AbstractClientConnection {
    protected int ping;
    private boolean isServerClosed;

    public NettyClientConnection(IMessageHandler commonWSMessageHandler, Channel channel) {
        super(commonWSMessageHandler, channel);
        ping = 0;
        isServerClosed = false;
        ComponentManager.getInstance().getComponent(ClientConnectComponent.class).addClientConnection(this);
    }

    @Override
    public void onDisconnect() {
        super.onDisconnect();
        ComponentManager.getInstance().getComponent(ClientConnectComponent.class).removeClientConnection(this);


    }

    @Override
    public void setPing(int ping) {
        this.ping = ping;
    }

    @Override
    public int getPing() {
        return ping;
    }

    @Override
    public void setServerClosed(boolean serverClosed) {
        isServerClosed = serverClosed;
    }

    @Override
    public boolean isServerClosed() {
        return isServerClosed;
    }
}

package com.zemel.framework.socket.netty.server;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.component.AbstractBridgeClientsComponent;
import com.zemel.framework.factory.NettyCommonCodecFactory;
import com.zemel.framework.socket.netty.IServerPacketHandler;

/**
 * @Author: zemel
 * @Date: 2020/2/9 18:14
 */
public class ServerConnector extends NettyServerConnector {
    private int serverId;

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public ServerConnector(int port, String host, IServerPacketHandler packetHandler, Class<NettyCommonCodecFactory> nettyCodecFactory) {
        super(port, host, packetHandler, nettyCodecFactory);
    }

    @Override
    public void disconnect() {
        ComponentManager.getInstance().getComponent(AbstractBridgeClientsComponent.class).removeBridgeClient(this);
    }
}

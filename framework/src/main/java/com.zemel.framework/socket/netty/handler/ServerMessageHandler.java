package com.zemel.framework.socket.netty.handler;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.bean.ConversionMessage;
import com.zemel.framework.component.ISelfDrivenControllerComponent;
import com.zemel.framework.socket.net.CommonMessage;
import com.zemel.framework.socket.netty.IServerConnector;
import com.zemel.framework.socket.netty.IServerPacketHandler;
import com.zemel.framework.socket.netty.server.NettyServerConnector;
import com.zemel.framework.thread.SelfDrivenRunnableAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerMessageHandler implements IServerPacketHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(ServerMessageHandler.class);

    @Override
    public void process(IServerConnector conn, Object packet) {

        LOGGER.error("message");
        CommonMessage message = ((CommonMessage) packet);
        short code = message.getCode();
        ComponentManager instance = ComponentManager.getInstance();
        ((NettyServerConnector) conn).addCommandTask(new SelfDrivenRunnableAction(() -> {
            ComponentManager.getInstance().getComponent(ISelfDrivenControllerComponent.class).invokeClass(new ConversionMessage(code, message.getBody()), conn);
        }));
        LOGGER.error("cmd");

    }

}

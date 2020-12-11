package com.zemel.framework.socket.netty.handler;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.bean.ConversionMessage;
import com.zemel.framework.component.ISelfDrivenControllerComponent;
import com.zemel.framework.component.ServerComponent;
import com.zemel.framework.socket.net.CommonMessage;
import com.zemel.framework.socket.netty.IClientConnection;
import com.zemel.framework.socket.netty.IConnectionHolder;
import com.zemel.framework.socket.netty.IMessageHandler;
import com.zemel.framework.thread.SelfDrivenRunnableAction;

public class CommonMessageHandler implements IMessageHandler {

    @Override
    public void process(IClientConnection conn, Object packet) {
        LOGGER.error("message");
        CommonMessage message = ((CommonMessage) packet);
        short code = message.getCode();
        IConnectionHolder holder = conn.getHolder();
        if (holder == null) {
            ComponentManager.getInstance().getSingleThreadExecutor().submit(new Thread(() -> {
                ComponentManager.getInstance().getComponent(ServerComponent.class).loginClient(conn, message.getBody());
            }));
        } else {
            holder.addCommandTask(new SelfDrivenRunnableAction(() -> {
                ComponentManager.getInstance().getComponent(ISelfDrivenControllerComponent.class).invokeClass(new ConversionMessage(code, message.getBody()), holder);
            }));
        }
    }

}

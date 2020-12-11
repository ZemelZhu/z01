package com.zemel.framework.socket.netty.handler;

import com.zemel.data.proto.entiy.CommonMsg;
import com.zemel.framework.ComponentManager;
import com.zemel.framework.bean.ConversionMessage;
import com.zemel.framework.component.ISelfDrivenControllerComponent;
import com.zemel.framework.component.ServerComponent;
import com.zemel.framework.socket.netty.IClientConnection;
import com.zemel.framework.socket.netty.IConnectionHolder;
import com.zemel.framework.socket.netty.IMessageHandler;
import com.zemel.framework.thread.SelfDrivenRunnableAction;

/**
 * @Author: zemel
 * @Date: 2020/2/9 11:54
 */
public class CommonWSMessageHandler implements IMessageHandler {
    @Override
    public void process(IClientConnection conn, Object packet) {
        LOGGER.error("message");
        CommonMsg.CommonMsgPB message = ((CommonMsg.CommonMsgPB) packet);
        int code = message.getCode();
        byte[] bytes = message.getBody().toByteArray();
        IConnectionHolder holder = conn.getHolder();
        if (holder == null) {
            ComponentManager.getInstance().getSingleThreadExecutor().submit(new Thread(() -> {
                ComponentManager.getInstance().getComponent(ServerComponent.class).loginClient(conn, bytes);
            }));
        } else {
            holder.addCommandTask(new SelfDrivenRunnableAction(() -> {
                ComponentManager.getInstance().getComponent(ISelfDrivenControllerComponent.class).invokeClass(new ConversionMessage((short) code, bytes), holder);
            }));
        }
    }
}

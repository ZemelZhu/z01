package com.zemel.framework.socket.net;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.component.ServerComponent;
import com.zemel.framework.socket.netty.IClientConnection;
import com.zemel.framework.socket.netty.IConnectionHolder;
import com.zemel.framework.thread.SelfDrivenCallableAction;
import com.zemel.framework.thread.SelfDrivenRunnableAction;
import com.zemel.framework.thread.SelfDrivenTaskQueue;

/**
 * @Author: zemel
 * @Date: 2020/2/8 14:28
 */
public class ServerClient implements IConnectionHolder {
    private IClientConnection connection;
    private int serverID;
    private SelfDrivenTaskQueue cmdQueue;

    public ServerClient() {
        this.cmdQueue = new SelfDrivenTaskQueue(ComponentManager.getInstance().getCommonThreadPool());
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }

    @Override
    public void onDisconnect() {
        ComponentManager.getInstance().getComponent(ServerComponent.class).removeServerClient(this);
    }

    @Override
    public IClientConnection getClientConnection() {
        return connection;
    }

    @Override
    public void setClientConnection(IClientConnection conn) {
        this.connection = conn;
    }


    @Override
    public void addCommandTask(SelfDrivenCallableAction task) {
        task.setActionQueue(cmdQueue);
        cmdQueue.add(task);
    }

    @Override
    public void addCommandTask(SelfDrivenRunnableAction task) {
        task.setActionQueue(cmdQueue);
        cmdQueue.add(task);
    }

    public int getServerID() {
        return serverID;
    }

    @Override
    public String toString() {
        return "ServerClient{" +
                "connection=" + connection +
                ", serverID=" + serverID +
                ", cmdQueue=" + cmdQueue +
                '}';
    }
}

package com.zemel.framework.socket.netty;

import com.zemel.framework.thread.ISequenceTask;

public interface IConnectionHolder extends ISequenceTask {
    /**
     * 连接关闭时的回调。
     */
    void onDisconnect();

    /**
     * 获取持有的连接。
     *
     * @return
     */
    IClientConnection getClientConnection();

    /**
     * 设置持有的连接。
     *
     * @param conn
     */
    void setClientConnection(IClientConnection conn);

}

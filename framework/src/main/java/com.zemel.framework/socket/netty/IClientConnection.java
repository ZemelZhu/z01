package com.zemel.framework.socket.netty;

public interface IClientConnection {
    /**
     * 获取客户端IP
     *
     * @return
     */
    String getClientIP();

    boolean send(Object msg);

    void onDisconnect();

    boolean isConnected();

    IMessageHandler getPacketHandler();

    IConnectionHolder getHolder();

    void setHolder(IConnectionHolder holder);

    void closeConnection(boolean immediately);

    void setPing(int ping);

    int getPing();

    /**
     * 是否是服务器断开连接
     *
     * @return
     */
    boolean isServerClosed();

    /**
     * 设置是否是服务器断开
     *
     * @param isServerClosed
     */
    void setServerClosed(boolean isServerClosed);
}

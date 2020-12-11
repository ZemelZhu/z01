package com.zemel.framework.socket.netty;

public interface IServerConnector {

    int getPort();

    String getHost();

    boolean connect();

    void disconnect();

    boolean isConnected();

    void send(Object msg);

    /**
     * 获取服务器包处理器
     */
    IServerPacketHandler getPacketHandler();

    /**
     * 读取缓冲byte[]
     */
    public byte[] getReadBytes();

    public void setChannelReadBytes();
}

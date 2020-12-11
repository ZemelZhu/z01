package com.zemel.framework.socket.netty;

public interface ServerAdapter {
    boolean start();

    void stop();

    int getPort();

    String getServerName();
}

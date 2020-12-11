package com.zemel.framework.socket.netty;

public interface IServerPacketHandler {

    void process(IServerConnector conn, Object msg);

}

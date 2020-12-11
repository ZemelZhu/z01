package com.zemel.framework.config;


import com.zemel.framework.socket.netty.IClientConnection;
import com.zemel.framework.socket.netty.IServerConnector;

import io.netty.util.AttributeKey;

public class CommonConst {
    /**
     * 客户端连接
     */
    public static final AttributeKey<IClientConnection> CLIENT_CON = AttributeKey.valueOf("CLIENT_CON");
    /**
     * 服务器连接器
     */
    public static final AttributeKey<IServerConnector> SERVER_CON = AttributeKey.valueOf("SERVER_CON");
    /**
     * 客户端真实IP
     */
    public static final AttributeKey<String> CLIENT_IP = AttributeKey.valueOf("CLIENT_IP");

    public static final AttributeKey<byte[]> READ_BYTES = AttributeKey.valueOf("READ_BYTES");
}

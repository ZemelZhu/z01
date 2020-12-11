package com.zemel.framework.socket.netty.net;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import com.zemel.framework.config.CommonConst;
import com.zemel.framework.socket.netty.IServerConnector;
import com.zemel.framework.socket.netty.IServerPacketHandler;

import io.netty.channel.Channel;

public abstract class AbstractServerConnector implements IServerConnector {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractServerConnector.class);

    protected Channel channel;
    private IServerPacketHandler packetHandler;
    protected byte[] readBytes;
    private int port;
    private String host;

    public AbstractServerConnector(int port, String host, IServerPacketHandler packetHandler) {
        this.port = port;
        this.host = host;
        this.packetHandler = packetHandler;
        this.readBytes = new byte[65534];
    }

    @Override
    public void setChannelReadBytes() {
        channel.attr(CommonConst.READ_BYTES).set(readBytes);
    }


    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public byte[] getReadBytes() {
        return readBytes;
    }

    @Override
    public boolean isConnected() {
        if (channel != null && channel.isActive()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void send(Object msg) {
        if (isConnected()) {
            channel.writeAndFlush(msg);
        }

    }

    @Override
    public IServerPacketHandler getPacketHandler() {
        return packetHandler;
    }

}

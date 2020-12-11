package com.zemel.framework.socket.netty.client;

import com.zemel.framework.config.CommonConst;
import com.zemel.framework.socket.netty.IClientConnection;
import com.zemel.framework.socket.netty.IConnectionHolder;
import com.zemel.framework.socket.netty.IMessageHandler;
import com.zemel.framework.until.StackMessagePrint;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public abstract class AbstractClientConnection implements IClientConnection {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractClientConnection.class);
    protected Channel channel;
    protected IMessageHandler messageHandler;
    protected byte[] readBytes;
    /**
     * 连接持有者
     */
    protected IConnectionHolder holder;

    public AbstractClientConnection(IMessageHandler messageHandler, Channel channel) {
        this.messageHandler = messageHandler;
        this.channel = channel;
        readBytes = new byte[65534];
        setChannelReadBytes();
    }

    public void setChannelReadBytes() {
        channel.attr(CommonConst.READ_BYTES).set(readBytes);
    }

    @Override
    public boolean send(Object msg) {
        if (isConnected()) {
            channel.writeAndFlush(msg);
            return true;
        }
        return false;
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
    public IMessageHandler getPacketHandler() {
        return messageHandler;
    }

    @Override
    public String getClientIP() {
        InetSocketAddress insocket = (InetSocketAddress) channel.remoteAddress();
        if (insocket != null)
            return insocket.getAddress().getHostAddress();
        return "";
    }

    @Override
    public IConnectionHolder getHolder() {
        return holder;
    }

    @Override
    public void setHolder(IConnectionHolder holder) {
        this.holder = holder;
        if (this.holder != null) {
            this.holder.setClientConnection(this);
        }
    }

    @Override
    public void closeConnection(boolean immediately) {
        if (isConnected()) {
            String s = StackMessagePrint.printStackTrace();
            LOGGER.error("close challel" + s);
            channel.close();
        }
    }

    @Override
    public void onDisconnect() {
        if (holder != null) {
            holder.onDisconnect();
        }

    }
}

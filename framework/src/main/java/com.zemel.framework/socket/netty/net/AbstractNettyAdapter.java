package com.zemel.framework.socket.netty.net;

import com.zemel.framework.socket.netty.ServerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public abstract class AbstractNettyAdapter implements ServerAdapter {
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractNettyAdapter.class);
    private int port;
    private String serverName;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    public AbstractNettyAdapter(int port, String serverName) {
        this.port = port;
        this.serverName = serverName;
    }

    @Override
    public boolean start() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootStrap = new ServerBootstrap();
            bootStrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);
            acceptorInit(bootStrap);
            bootStrap.bind(getPort()).sync();
            return true;
        } catch (Exception e) {
            LOGGER.error("netty Error:" + e.toString());
            return false;
        } finally {
            LOGGER.error("netty start" + getPort());
        }
    }

    @Override
    public void stop() {
        if (bossGroup != null)
            bossGroup.shutdownGracefully();
        if (workerGroup != null)
            workerGroup.shutdownGracefully();
    }

    @Override
    public int getPort() {
        return port;
    }


    protected abstract void acceptorInit(ServerBootstrap bootStrap);

    @Override
    public String getServerName() {
        return serverName;
    }

}

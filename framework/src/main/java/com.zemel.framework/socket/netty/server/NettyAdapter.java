package com.zemel.framework.socket.netty.server;

import com.zemel.framework.factory.NettyCommonCodecFactory;
import com.zemel.framework.socket.netty.handler.ClientNettyHandler;
import com.zemel.framework.socket.netty.net.AbstractNettyAdapter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;

/**
 * netty 自定义协议服务端
 *
 * @author zemel
 */
public class NettyAdapter extends AbstractNettyAdapter {

    public NettyAdapter(int port) {
        super(port, NettyAdapter.class.getSimpleName());
    }

    @Override
    protected void acceptorInit(ServerBootstrap bootStrap) {
        bootStrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast("codec", new NettyCommonCodecFactory());
                ch.pipeline().addLast(new ClientNettyHandler());
            }

        }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
    }

}

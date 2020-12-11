package com.zemel.framework.socket.netty.websocket;

import com.zemel.framework.socket.netty.handler.WebSocketFrameHandler;
import com.zemel.framework.socket.netty.net.AbstractNettyAdapter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;

/**
 * websocket 服务端
 *
 * @author zemel
 */
public class NettyWebsocketAdapter extends AbstractNettyAdapter {
    private String address;

    public NettyWebsocketAdapter(int port, String address) {
        super(port, NettyWebsocketAdapter.class.getSimpleName());
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    protected void acceptorInit(ServerBootstrap bootStrap) {
        bootStrap.childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("http-codec", new HttpServerCodec());
                pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
                pipeline.addLast("Compression", new WebSocketServerCompressionHandler());
                pipeline.addLast("Protocol", new WebSocketServerProtocolHandler(getAddress(), null, true));
                pipeline.addLast("handler", new WebSocketFrameHandler());
            }
        });
    }

}

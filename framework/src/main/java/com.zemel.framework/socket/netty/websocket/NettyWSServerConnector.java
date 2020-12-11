package com.zemel.framework.socket.netty.websocket;

import com.zemel.framework.config.CommonConst;
import com.zemel.framework.socket.netty.IServerPacketHandler;
import com.zemel.framework.socket.netty.handler.NettyWSServerConnectorHandler;
import com.zemel.framework.socket.netty.net.AbstractServerConnector;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketClientCompressionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * websocket 客户端
 *
 * @author zemel
 */
public class NettyWSServerConnector extends AbstractServerConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyWSServerConnector.class);

    /**
     * 处理器
     */
    private ChannelInboundHandlerAdapter handler = null;

    private static EventLoopGroup group = new NioEventLoopGroup();

    private static Bootstrap bootstrap = new Bootstrap();

    private static boolean setGroup = false;

    private String address;

    private static synchronized void init() {
        if (!setGroup) {
            bootstrap.group(group).channel(NioSocketChannel.class);
            setGroup = true;
        }
    }

    public NettyWSServerConnector(int port, String address, String host, IServerPacketHandler packetHandler) {
        super(port, host, packetHandler);
        this.address = address;
    }

    @Override
    public boolean connect() {
        try {
            NettyWSServerConnector.init();
            String URL = System.getProperty("url",
                    String.format("ws://%s:%s%s", getHost(), getPort(), getAddress()));
            URI uri = new URI(URL);
            handler = new NettyWSServerConnectorHandler(WebSocketClientHandshakerFactory.newHandshaker(uri,
                    WebSocketVersion.V13, null, true, new DefaultHttpHeaders()));

            ChannelFuture cf = connect(uri, handler);

            ((NettyWSServerConnectorHandler) handler).handshakeFuture().sync();
            channel = cf.channel();
            channel.attr(CommonConst.SERVER_CON).set(this);
            setChannelReadBytes();
            return true;
        } catch (Exception e) {
            LOGGER.error("Connector Error", e);
            return false;
        }
    }

    private static synchronized ChannelFuture connect(URI uri, ChannelInboundHandlerAdapter handler) {
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new HttpClientCodec(), new HttpObjectAggregator(8192),
                            WebSocketClientCompressionHandler.INSTANCE, handler);
                }
            }
        });
        try {
            ChannelFuture cf = bootstrap.connect(uri.getHost(), uri.getPort()).sync();
            return cf;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public void disconnect() {
        if (channel != null) {
            channel.close();
            channel = null;
        }
    }

    @Override
    public boolean isConnected() {
        if (channel != null && channel.isActive()) {
            return true;
        } else {
            return false;
        }
    }

    public void send(Object msg) {
        if (isConnected()) {
            channel.writeAndFlush(msg);
        }
    }

}

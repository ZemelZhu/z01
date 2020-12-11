package com.zemel.framework.socket.netty.server;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.config.CommonConst;
import com.zemel.framework.factory.NettyCommonCodecFactory;
import com.zemel.framework.socket.netty.IServerPacketHandler;
import com.zemel.framework.socket.netty.handler.NettyServerConnectorHandler;
import com.zemel.framework.socket.netty.net.AbstractServerConnector;
import com.zemel.framework.thread.ISequenceTask;
import com.zemel.framework.thread.SelfDrivenCallableAction;
import com.zemel.framework.thread.SelfDrivenRunnableAction;
import com.zemel.framework.thread.SelfDrivenTaskQueue;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * netty 自定义协议客户端
 *
 * @author zemel
 */
public class NettyServerConnector extends AbstractServerConnector implements ISequenceTask {
    private SelfDrivenTaskQueue cmdQueue;
    /**
     * 处理器
     */
    private Class<NettyServerConnectorHandler> handler;

    /**
     * 编码解码工厂
     */
    private Class<NettyCommonCodecFactory> codec;

    private EventLoopGroup group = new NioEventLoopGroup();

    private Bootstrap bootstrap = new Bootstrap();

    private boolean setGroup;

    public NettyServerConnector(int port, String host, IServerPacketHandler packetHandler,
                                Class<NettyCommonCodecFactory> nettyCodecFactory) {
        super(port, host, packetHandler);
        handler = NettyServerConnectorHandler.class;
        this.codec = nettyCodecFactory;
        this.cmdQueue = new SelfDrivenTaskQueue(ComponentManager.getInstance().getCommonThreadPool());
    }

    private synchronized void init() {
        if (!setGroup) {
            bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true);
            setGroup = true;
        }
    }

    @Override
    public boolean connect() {
        try {
            this.init();

            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast("codec", codec.newInstance());
                    ch.pipeline().addLast(handler.newInstance());

                }
            });
            ChannelFuture cf = bootstrap.connect(getHost(), getPort()).sync();
            // bootstrap.connect().
            // ChannelFuture cf = bootstrap.connect("127.0.0.1", 5501).sync();
            channel = cf.channel();
            channel.attr(CommonConst.SERVER_CON).set(this);
            setChannelReadBytes();
            return true;
        } catch (Exception e) {
            LOGGER.error("Connector Error", e);
            return false;
        }
    }

    @Override
    public void disconnect() {
        LOGGER.error("disconnect");
    }

    @Override
    public void addCommandTask(SelfDrivenCallableAction task) {
        task.setActionQueue(cmdQueue);
        cmdQueue.add(task);
    }

    @Override
    public void addCommandTask(SelfDrivenRunnableAction task) {
        task.setActionQueue(cmdQueue);
        cmdQueue.add(task);
    }
}

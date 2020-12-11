package com.zemel.framework.socket.netty.handler;

import org.slf4j.Logger;


import org.slf4j.LoggerFactory;

import com.zemel.framework.config.CommonConst;
import com.zemel.framework.socket.netty.IServerConnector;
import com.zemel.framework.until.StackMessagePrint;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * netty 自定义协议 服务端包处理器
 *
 * @author zemel
 */
public class NettyServerConnectorHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServerConnectorHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        IServerConnector conn = ctx.channel().attr(CommonConst.SERVER_CON).get();
        conn.getPacketHandler().process(conn, msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        IServerConnector conn = ctx.channel().attr(CommonConst.SERVER_CON).get();
        conn.disconnect();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error(StackMessagePrint.printErrorTrace(cause));
    }
}

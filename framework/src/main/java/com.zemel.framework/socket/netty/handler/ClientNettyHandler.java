package com.zemel.framework.socket.netty.handler;

import com.zemel.framework.config.CommonConst;
import com.zemel.framework.socket.netty.IClientConnection;
import com.zemel.framework.socket.netty.client.NettyClientConnection;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * netty 自定义协议客户端对象处理器
 *
 * @author zemel
 */
public class ClientNettyHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        IClientConnection conn = new NettyClientConnection(new CommonMessageHandler(), ctx.channel());
        ctx.channel().attr(CommonConst.CLIENT_CON).set(conn);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        IClientConnection conn = ctx.channel().attr(CommonConst.CLIENT_CON).get();
        conn.getPacketHandler().process(conn, msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        IClientConnection conn = ctx.channel().attr(CommonConst.CLIENT_CON).get();
        conn.onDisconnect();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // TODO Auto-generated method stub
        // super.exceptionCaught(ctx, cause);
    }
}

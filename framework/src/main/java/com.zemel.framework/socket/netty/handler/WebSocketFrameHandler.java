package com.zemel.framework.socket.netty.handler;

import com.zemel.data.proto.entiy.CommonMsg;
import com.zemel.framework.config.CommonConst;
import com.zemel.framework.socket.netty.IClientConnection;
import com.zemel.framework.socket.netty.client.NettyClientConnection;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * netty websocket 客户端对象处理器
 *
 * @author zemel
 */
public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketFrameHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        if (frame instanceof TextWebSocketFrame) {
            logger.error(((TextWebSocketFrame) frame).text());
        } else {
            if ((frame instanceof BinaryWebSocketFrame)) {
                int len = frame.content().readableBytes();
                byte[] readBytes = new byte[len];
                frame.content().readBytes(readBytes);
                //  logger.error(Arrays.toString(readBytes));
                IClientConnection conn = ctx.channel().attr(CommonConst.CLIENT_CON).get();
                CommonMsg.CommonMsgPB commonMsgPB = CommonMsg.CommonMsgPB.parseFrom(readBytes);
                conn.getPacketHandler().process(conn, commonMsgPB);
//                ByteBuf buf = Unpooled.wrappedBuffer(readBytes);
//                conn.send(new BinaryWebSocketFrame(buf));
            }
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        IClientConnection conn = new NettyClientConnection(new CommonWSMessageHandler(), ctx.channel());
        ctx.channel().attr(CommonConst.CLIENT_CON).set(conn);
        logger.error("active" + ctx.channel().toString());
//        new Thread(()->{
//        	while(true)
//        	{
//        		try {
//					Thread.sleep(4000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//        		String str = UUID.randomUUID().toString();
//
//        		ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(str.getBytes());
//        		BinaryWebSocketFrame textWebSocketFrame = new BinaryWebSocketFrame(wrappedBuffer);
//        		if(ctx.channel()!=null&&ctx.channel().isActive())
//        		{
//        			ctx.channel().writeAndFlush(textWebSocketFrame);
////        			logger.error(""+str);
//        		}
//        	}
//        }).start();


    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        super.channelInactive(ctx);
        logger.error("disconnect" + ctx);
        IClientConnection conn = ctx.channel().attr(CommonConst.CLIENT_CON).get();
        if (!conn.isServerClosed())
            conn.onDisconnect();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        logger.error("心跳");
    }
}

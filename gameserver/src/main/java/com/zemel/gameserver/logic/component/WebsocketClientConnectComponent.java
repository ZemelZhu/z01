package com.zemel.gameserver.logic.component;

import com.google.protobuf.ByteString;
import com.zemel.data.proto.entiy.CommonMsg;
import com.zemel.data.protocol.Protocol;
import com.zemel.framework.component.ClientConnectComponent;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.springframework.stereotype.Component;

/**
 * @Author: zemel
 * @Date: 2020/2/14 15:23
 */
@Component
public class WebsocketClientConnectComponent extends ClientConnectComponent {
    private byte[] pingPackage;
    @Override
    public boolean initialize() {
        CommonMsg.CommonMsgPB.Builder builder = CommonMsg.CommonMsgPB.newBuilder();
        builder.setCode(Protocol.PING);
        builder.setBody(ByteString.EMPTY);
        pingPackage = builder.build().toByteArray();
        return super.initialize();
    }

    @Override
    protected Object getPingPackage() {
        ByteBuf buf = Unpooled.wrappedBuffer(pingPackage);
        return new BinaryWebSocketFrame(buf);
    }


}

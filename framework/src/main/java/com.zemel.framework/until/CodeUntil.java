package com.zemel.framework.until;


import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CodeUntil {
    private static final Logger logger = LoggerFactory.getLogger(CodeUntil.class);

    public static byte[] deCode(byte[] readBytes) {
//        try {
//
//            CommonMsgPBOuterClass.User commonMsgPB = CommonMsgPBOuterClass.User.parseFrom(readBytes);
//            String content = commonMsgPB.getPassword();
//            logger.error("passwork" + content);
//            logger.error(commonMsgPB.toBuilder().toString());
//            return commonMsgPB.toByteArray();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }

    public static Object deCode1(byte[] bytes) {
        BinaryWebSocketFrame binaryWebSocketFrame = new BinaryWebSocketFrame(Unpooled.wrappedBuffer(bytes));
        return binaryWebSocketFrame;
    }
}

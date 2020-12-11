package com.zemel.tool.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zemel.data.proto.entiy.CommonMsg;
import com.zemel.data.proto.entiy.LoginMsg;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @Author: zemel
 * @Date: 2020/2/13 13:59
 */
@SpringBootTest
public class ProtobufTest {
    private static final Logger logger = LoggerFactory.getLogger(ProtobufTest.class);
    @Test
    void test(){
        CommonMsg.CommonMsgPB.Builder builder = CommonMsg.CommonMsgPB.newBuilder();
        LoginMsg.LoginMsgCS.Builder builder1 = LoginMsg.LoginMsgCS.newBuilder();
        builder1.setUserID(1);
        builder1.setPassword("123");
        builder.setBody(builder1.build().toByteString());
        builder.setCode(Integer.MAX_VALUE);
        byte[] bytes = builder.build().toByteArray();
        logger.error("bytes:"+ Arrays.toString(bytes));
        byte[] test = {8,1,16,1};
        try {
            CommonMsg.CommonMsgPB commonMsgPB = CommonMsg.CommonMsgPB.parseFrom(test);
            logger.error(commonMsgPB.toBuilder().toString());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}

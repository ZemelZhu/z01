package com.zemel.framework.bean;

import com.google.protobuf.ByteString;
import com.zemel.data.proto.entiy.CommonMsg;

/**
 * @Author: zemel
 * @Date: 2020/2/10 11:00
 */
public interface ICorePlayer {
    int getPlayerID();

    void send(Object message);

    void send(ByteString message);

    void send(CommonMsg.CommonMsgPB message);
}

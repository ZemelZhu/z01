package com.zemel.gameserver.logic.controller;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zemel.data.proto.entiy.LoginMsg;
import com.zemel.data.proto.entiy.WebServerMsg;
import com.zemel.data.protocol.Protocol;
import com.zemel.framework.annotation.SelfDrivenRequestMapping;
import com.zemel.framework.bean.ConversionMessage;
import com.zemel.framework.annotation.ISelfDrivenController;
import com.zemel.framework.socket.net.CommonMessage;
import com.zemel.framework.socket.netty.server.ServerConnector;
import com.zemel.gameserver.logic.component.BridgeClientsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@SelfDrivenRequestMapping(Protocol.LOGIN)
public class PlayerController implements ISelfDrivenController {
    @Autowired
    private BridgeClientsComponent bridgeClientsComponent;

    private int getA() {
        LOGGER.error("1");
        return 1;
    }
    @SelfDrivenRequestMapping(LoginMsg.LoginCmdType.Login_VALUE)
    public int getB() {
        LOGGER.error("2");
        return 2;

    }
    @SelfDrivenRequestMapping(LoginMsg.LoginCmdType.NewFriend_VALUE)
    public int getC(Object ob) {
        LOGGER.error("3");

        return 3;
    }
    @SelfDrivenRequestMapping(LoginMsg.LoginCmdType.Error_VALUE)
    public int getD(LoginMsg.PlayerInfoPB pb) {
        LOGGER.error("4 type:"+pb.getType());
        Collection<ServerConnector> servers = bridgeClientsComponent.getServers();
        CommonMessage commonMessage = new CommonMessage((short) Protocol.NOTIFY_WEBSERVER);
        WebServerMsg.NotifyWebServer.Builder builder = WebServerMsg.NotifyWebServer.newBuilder();
        builder.setId(1);
        builder.setType(WebServerMsg.MessageType.Error);
        commonMessage.setBody(builder.build().toByteArray());
        for (ServerConnector server : servers) {
            server.send(commonMessage);
        }

        return 4;
    }

    @Override
    public void processData(ConversionMessage conversionMessage) throws InvalidProtocolBufferException {
        LoginMsg.PlayerInfoPB playerInfoPB = LoginMsg.PlayerInfoPB.parseFrom(conversionMessage.getMessageBody());
        LoginMsg.LoginCmdType type = playerInfoPB.getType();
        int number = type.getNumber();
        conversionMessage.setMethodId(number);
        conversionMessage.setMessage(playerInfoPB);

    }

    @Override
    public boolean invoke(Object holder) {
        return true;
    }
}

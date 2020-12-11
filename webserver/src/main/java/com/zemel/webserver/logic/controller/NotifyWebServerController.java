package com.zemel.webserver.logic.controller;

import com.zemel.data.proto.entiy.WebServerMsg;
import com.zemel.data.protocol.Protocol;
import com.zemel.framework.annotation.SelfDrivenRequestMapping;
import com.zemel.framework.bean.ConversionMessage;
import com.zemel.framework.annotation.ISelfDrivenController;
import org.springframework.stereotype.Service;

/**
 * @Author: zemel
 * @Date: 2020/2/9 19:03
 */
@Service
@SelfDrivenRequestMapping(Protocol.NOTIFY_WEBSERVER)
public class NotifyWebServerController implements ISelfDrivenController {
    @SelfDrivenRequestMapping(WebServerMsg.MessageType.Error_VALUE)
    public void getA(WebServerMsg.NotifyWebServer playerInfoPB)
    {
        LOGGER.error("GETA A A"+playerInfoPB.toString());
    }
    @Override
    public void processData(ConversionMessage conversionMessage) throws Exception {
        WebServerMsg.NotifyWebServer playerInfoPB = WebServerMsg.NotifyWebServer.parseFrom(conversionMessage.getMessageBody());
        conversionMessage.setMethodId(playerInfoPB.getType().getNumber());
        conversionMessage.setMessage(playerInfoPB);
    }

    @Override
    public boolean invoke(Object holder) {
        return true;
    }
}

package com.zemel.gameserver.logic.controller;

import com.zemel.data.protocol.Protocol;
import com.zemel.framework.annotation.SelfDrivenRequestMapping;
import com.zemel.framework.bean.ConversionMessage;
import com.zemel.framework.annotation.ISelfDrivenController;
import com.zemel.framework.socket.netty.IConnectionHolder;
import org.springframework.stereotype.Service;

/**
 * @Author: zemel
 * @Date: 2020/2/17 15:29
 */
@Service
@SelfDrivenRequestMapping(Protocol.PING)
public class PingController implements ISelfDrivenController {
    @Override
    public void processData(ConversionMessage conversionMessage) throws Exception {

    }

    @Override
    public boolean invoke(Object holder) {
        ((IConnectionHolder) holder).getClientConnection().setPing(0);
        return false;
    }
}

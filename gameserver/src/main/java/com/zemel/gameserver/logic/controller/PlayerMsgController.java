package com.zemel.gameserver.logic.controller;

import com.zemel.data.proto.entiy.PlayerMsg;
import com.zemel.data.protocol.Protocol;
import com.zemel.framework.annotation.SelfDrivenRequestMapping;
import com.zemel.framework.bean.ConversionMessage;
import com.zemel.framework.annotation.ISelfDrivenController;
import org.springframework.stereotype.Service;

/**
 * @Author: zemel
 * @Date: 2020/2/12 13:33
 */
@Service
@SelfDrivenRequestMapping(Protocol.PLAYER_MSG)
public class PlayerMsgController implements ISelfDrivenController {
    public void playerLogin(PlayerMsg.PlayerMsgCS playerMsgCS)
    {
        PlayerMsg.PlayerAccountPB playerAccountPB = playerMsgCS.getPlayerAccountPB();
        int playerID = playerAccountPB.getPlayerID();
        String password = playerAccountPB.getPassword();

    }
    @Override
    public void processData(ConversionMessage conversionMessage) throws Exception {
        PlayerMsg.PlayerMsgCS playerMsgCS = PlayerMsg.PlayerMsgCS.parseFrom(conversionMessage.getMessageBody());
        conversionMessage.setMethodId(playerMsgCS.getCmdTypeValue());
        conversionMessage.setMessage(playerMsgCS);
    }

    @Override
    public boolean invoke(Object holder) {
        return true;
    }
}

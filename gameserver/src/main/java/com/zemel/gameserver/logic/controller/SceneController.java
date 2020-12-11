package com.zemel.gameserver.logic.controller;

import com.zemel.data.proto.entiy.CommonMsg;
import com.zemel.data.proto.entiy.SceneMsg;
import com.zemel.data.protocol.Protocol;
import com.zemel.framework.annotation.SelfDrivenRequestMapping;
import com.zemel.framework.bean.ConversionMessage;
import com.zemel.framework.bean.room.BaseRoom;
import com.zemel.framework.annotation.ISelfDrivenController;
import com.zemel.gameserver.logic.player.GamePlayer;
import org.springframework.stereotype.Service;

/**
 * @Author: zemel
 * @Date: 2020/2/13 16:37
 */
@Service
@SelfDrivenRequestMapping(Protocol.SCENE)
public class SceneController implements ISelfDrivenController {
    @SelfDrivenRequestMapping(SceneMsg.SceneCmdType.UpdatePosition_VALUE)
    public void updatePosition(SceneMsg.SceneMsgCS sceneMsgCS, GamePlayer gamePlayer)
    {
        LOGGER.error(""+sceneMsgCS.toBuilder().toString()+gamePlayer);
        sceneMsgCS = sceneMsgCS.toBuilder().setPlayerID(gamePlayer.getPlayerID()).build();
        CommonMsg.CommonMsgPB.Builder builder = CommonMsg.CommonMsgPB.newBuilder();
        builder.setCode(Protocol.SCENE);
        builder.setBody(sceneMsgCS.toByteString());
        BaseRoom room = gamePlayer.getRoom();
        if(room!=null)
        {
            room.broadcast(builder.build());
            room.updatePlace(sceneMsgCS);
        }
        gamePlayer.send(builder.build());
    }
    @Override
    public void processData(ConversionMessage conversionMessage) throws Exception {
        SceneMsg.SceneMsgCS sceneMsgCS = SceneMsg.SceneMsgCS.parseFrom(conversionMessage.getMessageBody());
        conversionMessage.setMethodId(sceneMsgCS.getCmdTypeValue());
        conversionMessage.setMessage(sceneMsgCS);
    }

    @Override
    public boolean invoke(Object holder) {
        return true;
    }
}

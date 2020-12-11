package com.zemel.gameserver.logic.controller;

import com.zemel.data.proto.entiy.RoomMsg;
import com.zemel.data.protocol.Protocol;
import com.zemel.framework.annotation.SelfDrivenRequestMapping;
import com.zemel.framework.bean.ConversionMessage;
import com.zemel.framework.bean.CorePlayer;
import com.zemel.framework.bean.room.BaseRoom;
import com.zemel.framework.annotation.ISelfDrivenController;
import com.zemel.gameserver.logic.component.RoomComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: zemel
 * @Date: 2020/2/10 13:26
 */
@Service
@SelfDrivenRequestMapping(Protocol.ROOM)
public class RoomController implements ISelfDrivenController {
    @Autowired
    private RoomComponent roomComponent;
    @SelfDrivenRequestMapping(RoomMsg.RoomCmdType.Cmd_CreateRoom_VALUE)
    public void createRoom(RoomMsg.RoomMsgCS roomMsgCS, CorePlayer corePlayer)
    {

    }
    @SelfDrivenRequestMapping(RoomMsg.RoomCmdType.Cmd_GetRoomInfo_VALUE)
    public void getRoomInfo(RoomMsg.RoomMsgCS roomMsgCS,CorePlayer corePlayer)
    {
        BaseRoom room = corePlayer.getRoom();
        if(room!=null)
            room.getRoomInfoData(corePlayer);
    }
    @SelfDrivenRequestMapping(RoomMsg.RoomCmdType.Cmd_EnterRoom_VALUE)
    public void enterRoom(RoomMsg.RoomMsgCS roomMsgCS,CorePlayer corePlayer)
    {
        int key = roomMsgCS.getKey();
        BaseRoom room = roomComponent.getRoomByPWD(key);
        if(room!=null)
        {
            room.addPlayer(corePlayer);
            corePlayer.setRoom(room);

        }
    }
    @Override
    public void processData(ConversionMessage conversionMessage) throws Exception {
        RoomMsg.RoomMsgCS roomMsgCS = RoomMsg.RoomMsgCS.parseFrom(conversionMessage.getMessageBody());
        conversionMessage.setMethodId(roomMsgCS.getCmdTypeValue());
        conversionMessage.setMessage(roomMsgCS);
    }

    @Override
    public boolean invoke(Object holder) {
        return true;
    }
}

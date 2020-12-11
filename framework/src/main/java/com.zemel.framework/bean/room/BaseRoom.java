package com.zemel.framework.bean.room;

import com.zemel.data.entiy.RoomInfo;
import com.zemel.data.proto.entiy.CommonMsg;
import com.zemel.data.proto.entiy.RoomMsg;
import com.zemel.data.proto.entiy.SceneMsg;
import com.zemel.data.protocol.Protocol;
import com.zemel.data.vo.PointVo;
import com.zemel.framework.bean.CorePlayer;
import com.zemel.framework.bean.ICorePlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zemel
 * @Date: 2020/2/14 22:39
 */
public class BaseRoom {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseRoom.class);
    protected RoomInfo roomInfo;
    protected Map<Integer, ICorePlayer> watchers;
    protected Map<Integer, PlaceInfo> places;

    public BaseRoom(RoomInfo roomInfo) {
        this.roomInfo = roomInfo;
        watchers = new HashMap<>();
        places = new HashMap<>();

    }

    public void addPlayer(ICorePlayer player) {
        watchers.put(player.getPlayerID(), player);
        if (!places.containsKey(player.getPlayerID()))
            places.put(player.getPlayerID(), new PlaceInfo(player));
        RoomMsg.RoomMsgSC.Builder builder = RoomMsg.RoomMsgSC.newBuilder();
//        for(PlaceInfo placeInfo: places.values())
//        {
//            RoomMsg.PlaceDataSC build = placeInfo.build();
//            builder.addPlaceData(build);
//        }
        builder.setRoomInfo(getRoomInfoPB());
        builder.setCmdType(RoomMsg.RoomCmdType.Cmd_EnterRoom);
        sendMessage(builder.build(), player);
        //sendMessageToAll(builder.build());

    }

    private RoomMsg.RoomInfoPB.Builder getRoomInfoPB() {
        RoomMsg.RoomInfoPB.Builder builder = RoomMsg.RoomInfoPB.newBuilder();
        builder.setCreatorID(roomInfo.getCreatorId());
        builder.setRoomType(roomInfo.getRoomType());
        return builder;
    }

    private void sendMessageToAll(RoomMsg.RoomMsgSC message) {
        CommonMsg.CommonMsgPB.Builder builder = CommonMsg.CommonMsgPB.newBuilder();
        builder.setCode(Protocol.ROOM);
        builder.setBody(message.toByteString());
        CommonMsg.CommonMsgPB build = builder.build();
        watchers.values().forEach(player -> {
            player.send(build);
        });
    }

    public void sendMessage(RoomMsg.RoomMsgSC message, ICorePlayer player) {
        CommonMsg.CommonMsgPB.Builder builder = CommonMsg.CommonMsgPB.newBuilder();
        builder.setCode(Protocol.ROOM);
        builder.setBody(message.toByteString());
        player.send(builder.build());
    }

    public void addWatcher(ICorePlayer player) {

    }

    public void removePlayer(ICorePlayer player) {

    }

    public void getRoomInfoData(CorePlayer corePlayer) {

        RoomMsg.RoomMsgSC.Builder builder = RoomMsg.RoomMsgSC.newBuilder();
        for (PlaceInfo placeInfo : places.values()) {
            RoomMsg.PlaceDataSC build = placeInfo.build();
            builder.addPlaceData(build);
            LOGGER.error("placeInfo:" + placeInfo);
        }
        builder.setCmdType(RoomMsg.RoomCmdType.Cmd_GetRoomInfo);
        //sendMessage(builder.build(),corePlayer);
        sendMessageToAll(builder.build());
    }

    public void broadcast(CommonMsg.CommonMsgPB build) {
        watchers.values().forEach(player -> {
            player.send(build);
        });
    }

    public void updatePlace(SceneMsg.SceneMsgCS sceneMsg) {
        int playerID = sceneMsg.getPlayerID();
        PlaceInfo placeInfo = places.get(playerID);
        if (placeInfo != null) {
            SceneMsg.PostionPB postion = sceneMsg.getTransform().getPosition();
            placeInfo.getTransformVo().setPosition(PointVo.builder().x(postion.getX()).y(postion.getY()).z(postion.getZ()).build());
            postion = sceneMsg.getTransform().getRotation();
            LOGGER.error("postion:  " + postion);
            placeInfo.getTransformVo().setRotation(PointVo.builder().x(postion.getX()).y(postion.getY()).w(postion.getW()).z(postion.getZ()).build());
        }
    }
}

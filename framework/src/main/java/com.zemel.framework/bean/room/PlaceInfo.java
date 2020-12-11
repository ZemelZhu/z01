package com.zemel.framework.bean.room;

import com.zemel.data.proto.entiy.RoomMsg;
import com.zemel.data.vo.PointVo;
import com.zemel.data.vo.TransformVo;
import com.zemel.framework.bean.ICorePlayer;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/2/15 15:17
 */
@Data
public class PlaceInfo {
    public enum PlaceType {
        NULL,
        UNPREPARE,
        PREPARE,
        PLAYING,
    }

    public PlaceInfo(ICorePlayer player) {
        this.player = player;
        PointVo point = PointVo.builder().x(5f).y(0.5f).z(3f).w(0f).build();
        PointVo rotation = PointVo.builder().x(0f).y(0f).z(0f).w(1.0f).build();
        this.transformVo = new TransformVo(point, rotation, point);
    }

    protected int placeID;
    protected TransformVo transformVo;
    protected ICorePlayer player;
    protected PlaceType placeType;

    public RoomMsg.PlaceDataSC build() {
        RoomMsg.PlaceDataSC.Builder builder = RoomMsg.PlaceDataSC.newBuilder();
        return builder.setTransform(transformVo.build()).setPlayerID(player.getPlayerID()).build();
    }
}

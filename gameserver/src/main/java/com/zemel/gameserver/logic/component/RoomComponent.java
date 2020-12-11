package com.zemel.gameserver.logic.component;

import com.zemel.data.entiy.RoomInfo;
import com.zemel.data.mapping.RoomInfoMapper;
import com.zemel.framework.bean.room.BaseRoom;
import com.zemel.framework.component.IComponent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class RoomComponent implements IComponent {

    private Map<Integer, RoomInfo> PWDInfos;

    private Map<Integer, BaseRoom> PWDRooms;
    @Resource
    private RoomInfoMapper roomInfoMapper;

    @Override
    public boolean initialize() {
        PWDInfos = new HashMap<>();
        PWDRooms = new HashMap<>();
        return true;
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean reload() {
        return false;
    }

    public BaseRoom getRoomByPWD(int key) {
        BaseRoom baseRoom = PWDRooms.get(key);
        if (baseRoom != null)
            return baseRoom;
        RoomInfo roomInfo = roomInfoMapper.selectById(key);
        if (roomInfo != null) {
            baseRoom = new BaseRoom(roomInfo);
            PWDRooms.put(key, baseRoom);
            return baseRoom;
        }
        return null;
    }
}

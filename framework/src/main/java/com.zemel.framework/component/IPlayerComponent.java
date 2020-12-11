package com.zemel.framework.component;

import com.zemel.data.entiy.PlayerInfo;
import com.zemel.data.mapping.PlayerInfoMapper;
import com.zemel.framework.socket.netty.IConnectionHolder;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class IPlayerComponent<T extends IConnectionHolder> implements IComponent {
    @Resource
    protected PlayerInfoMapper playerInfoMapper;
    protected Map<Integer, T> players;

    public T getPlayerById(int id) {
        return players.get(id);
    }

    public Collection<T> getAllPlayer() {
        return players.values();
    }

    public Set<Integer> getAllPlayerId() {
        return players.keySet();
    }

    @Override
    public boolean initialize() {
        players = new ConcurrentHashMap<>();
        return true;
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public void stop() {
        for (IConnectionHolder player : players.values()) {
            player.onDisconnect();
        }
    }

    @Override
    public boolean reload() {
        return false;
    }

    public void updatePlayerInfo(PlayerInfo playerInfo) {
        playerInfoMapper.updateById(playerInfo);
    }
}

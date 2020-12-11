package com.zemel.gameserver.logic.component;

import com.zemel.data.entiy.PlayerInfo;
import com.zemel.framework.component.IPlayerComponent;
import com.zemel.framework.socket.netty.IClientConnection;
import com.zemel.gameserver.logic.player.GamePlayer;
import org.springframework.stereotype.Component;

@Component
public class PlayerComponent extends IPlayerComponent<GamePlayer> {
    public void addPlayer(GamePlayer player)
    {
        players.put(player.getPlayerID(),player);
    }
    public void gamePlayerLogin(int userID, String password, IClientConnection conn) {
        PlayerInfo playerInfo = playerInfoMapper.selectById(userID);
        if(playerInfo!=null)
        {
            if(playerInfo.getPassword().equals(password))
            {
                GamePlayer gamePlayer = getPlayerById(playerInfo.getId());
                if(gamePlayer==null)
                {
                    gamePlayer = new GamePlayer(conn, playerInfo);
                    addPlayer(gamePlayer);
                }
                else
                {
                    gamePlayer.reconnection(conn);
                }
                conn.setHolder(gamePlayer);
            }
        }
    }
}

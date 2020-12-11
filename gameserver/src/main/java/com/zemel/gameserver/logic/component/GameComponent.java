package com.zemel.gameserver.logic.component;

import com.zemel.data.entiy.PlayerInfo;
import com.zemel.data.mapping.PlayerInfoMapper;
import com.zemel.framework.component.IComponent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zemel
 * @
 */
@Component
public class GameComponent implements IComponent {
    @Resource
    private PlayerInfoMapper playerInfoMapper;

    @Override
    public boolean initialize() {
        return true;
    }

    @Override
    public boolean start() {
        List<PlayerInfo> playerInfos = playerInfoMapper.selectList(null);
        playerInfos.forEach(f -> {
//            f.setIsExist(1);
        });
//        PlayerInfo playerInfo = new PlayerInfo();
//        playerInfo.setUpdateTime(new Date());
//        playerInfoMapper.insert(playerInfo);
        LOGGER.error(playerInfos + "");
        return true;
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean reload() {
        return false;
    }
}

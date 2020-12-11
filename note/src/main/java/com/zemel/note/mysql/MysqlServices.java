package com.zemel.note.mysql;

import com.zemel.data.entiy.PlayerInfo;
import com.zemel.data.mapping.PlayerInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * @Author: zemel
 * @Date: 2020/2/25 22:28
 */
@Component
public class MysqlServices {
    static final Logger LOGGER = LoggerFactory.getLogger(MysqlServices.class);
    @Autowired()
    private PlayerInfoMapper playerInfoMapper;
    @Transactional
    @PostConstruct
    public void init()
    {
        PlayerInfo playerInfo = playerInfoMapper.selectById(1);
        LOGGER.error(""+playerInfo);
        playerInfo.setCoin(new BigDecimal(1));
        playerInfoMapper.updateById(playerInfo);
    }
}

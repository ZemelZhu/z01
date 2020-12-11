package com.zemel.webserver.controller;

import com.zemel.data.entiy.PlayerInfo;
import com.zemel.data.mapping.PlayerInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zemel
 * @Date: 2020/2/11 20:19
 */
@CrossOrigin
@RestController
@RequestMapping("PlayerAccount")
public class PlayerAccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerAccountController.class);
    @Autowired
    private PlayerInfoMapper playerInfoMapper;
    @RequestMapping("Login")
    public boolean login(int playerID,String password)
    {
        LOGGER.error("playerId:"+playerID+" password:"+password);
        PlayerInfo playerInfo = playerInfoMapper.selectById(playerID);
        if(playerInfo!=null)
        {
            if(playerInfo.getPassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }
}

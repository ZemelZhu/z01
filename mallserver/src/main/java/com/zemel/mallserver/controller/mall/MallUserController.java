package com.zemel.mallserver.controller.mall;

import com.zemel.mallserver.services.mall.MallUserService;
import com.zemel.mallserver.vo.MallUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zemel
 * @Date: 2020/3/22 22:29
 */
@RestController
@CrossOrigin
@RequestMapping("mall/user")
public class MallUserController {
    @Autowired
    private MallUserService mallUserService;
    @RequestMapping("getInfoById")
    public MallUserInfoVo getInfoById(int id)
    {
        return mallUserService.getInfoById(id);
    }

}

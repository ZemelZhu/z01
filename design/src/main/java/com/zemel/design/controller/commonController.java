package com.zemel.design.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zemel
 * @Date: 2020/7/12 22:19
 */
@RestController
@Api(tags = "用户管理相关接口")
@RequestMapping("common")
public class commonController {
    @PostMapping("test")
    @ApiOperation("添加用户的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "用户名", defaultValue = "1"),
    })
    public String test(int key) {
        return "test" + key;
    }
}

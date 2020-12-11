package com.zemel.mallserver.controller.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zemel.data.type.Roles;
import com.zemel.framework.annotation.RolesAnnotation;
import com.zemel.mallserver.ao.CommodityAo;
import com.zemel.mallserver.services.common.CommodityService;
import com.zemel.mallserver.vo.CommodityVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zemel
 * @Date: 2020/3/22 11:28
 */
@RolesAnnotation(roles = Roles.ADMIN)
@RestController
@CrossOrigin
@RequestMapping("admin/commodity")
public class AdminCommodityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminCommodityController.class);
    @Autowired
    private CommodityService commodityService;

    @PostMapping("createOrder")
    public boolean createOrder(CommodityAo commodityDto) throws JsonProcessingException {
        LOGGER.error("createOrder:" + commodityDto);
        return commodityService.createOrder(commodityDto);

    }
    @GetMapping("getCommodityById")
    public CommodityVo getCommodityById(long id) throws JsonProcessingException {
        return commodityService.getCommodityById(id);

    }
    @PostMapping("delete")
    public boolean deleteCommodity(int id) {
        return commodityService.delete(id);
    }

    @PostMapping("mask")
    public boolean mask(int id) {
        return commodityService.mask(id);
    }
}

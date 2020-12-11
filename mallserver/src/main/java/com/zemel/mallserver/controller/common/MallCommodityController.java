package com.zemel.mallserver.controller.common;

import com.zemel.framework.until.StringUtil;
import com.zemel.mallserver.services.common.CommodityService;
import com.zemel.mallserver.vo.CommodityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/3/26 21:27
 */
@RestController
@CrossOrigin
@RequestMapping("common/mall/commodity")
public class MallCommodityController {
    @Autowired
    private CommodityService commodityService;

    @GetMapping("getMallShop")
    public List<CommodityVo> getMallShop(int page)
    {
        List<CommodityVo> mallShop = commodityService.getMallShop(page);
        return mallShop;
    }
    @GetMapping("getCommodityById")
    public CommodityVo getCommodityById(String id)
    {
        long commodityId = Long.valueOf(id);
        return commodityService.getCommodityById(commodityId);
    }
    @GetMapping("getCommodityBySearch")
    public List<CommodityVo> getCommodityBySearch(int page,String match)
    {
        if(StringUtil.isNullOrEmpty(match))
            return null;
        return commodityService.getCommodityBySearch(page,match);
    }
    @GetMapping("getMallShopByLabel")
    public List<CommodityVo> getMallShopByLabel(int page,int label)
    {
        return commodityService.getMallShopByLabel(page,label);
    }
    @GetMapping("getMallShopSuccessLabel")
    public List<CommodityVo> getMallShopSuccessLabel(int page)
    {
        return commodityService.getMallShopSuccessLabel(page);
    }
    @GetMapping("getMallAllShopCount")
    public int getMallAllShopCount()
    {
        return commodityService.getMallAllShopCount();
    }

}

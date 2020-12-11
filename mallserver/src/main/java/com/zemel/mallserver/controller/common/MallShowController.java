package com.zemel.mallserver.controller.common;

import com.zemel.framework.until.ServletUtil;
import com.zemel.framework.vo.CityInfoVo;
import com.zemel.framework.vo.ResponseVo;
import com.zemel.mallserver.services.common.CommodityService;
import com.zemel.mallserver.services.common.MallLabelService;
import com.zemel.mallserver.vo.CommodityVo;
import com.zemel.mallserver.vo.MallLabelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/4/5 11:10
 */
@CrossOrigin
@RestController
@RequestMapping("common/mall/show")
public class MallShowController {
    @Autowired
    private MallLabelService mallLabelService;
    @Autowired
    private CommodityService commodityService;

    @GetMapping("homeCarousel")
    public List<CommodityVo> homeCarousel() {
        List<CommodityVo> commodityVos = commodityService.homeCarousel();
        return commodityVos;
    }

    @GetMapping("homeLabel")
    public Collection<MallLabelVo> homeLabel() {
        Collection<MallLabelVo> mallLabelInfos = mallLabelService.homeLabel();
        return mallLabelInfos;
    }

    @GetMapping("getMallConfig")
    public ResponseVo getMallConfig() {
        return ResponseVo.build(mallLabelService.getMallConfig());
    }

    @GetMapping("getMallConfigById")
    public ResponseVo getMallConfigById(int id) {
        return ResponseVo.build(mallLabelService.getMallConfigById(id));
    }

    @GetMapping("getCityInfo")
    public CityInfoVo getCityInfo(HttpServletRequest request) {
        String requestIP = ServletUtil.getRequestIP(request);
        return ServletUtil.getCityInfo(requestIP);
    }
}

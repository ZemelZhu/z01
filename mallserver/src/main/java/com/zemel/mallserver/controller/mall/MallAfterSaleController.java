package com.zemel.mallserver.controller.mall;

import com.zemel.framework.until.TokenUtil;
import com.zemel.mallserver.ao.MallAfterSaleAo;
import com.zemel.mallserver.services.common.MallAfterSaleService;
import com.zemel.mallserver.vo.MallAfterSaleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/5/2 21:19
 */
@RestController
@CrossOrigin
@RequestMapping("mall/after/sale")
public class MallAfterSaleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MallAfterSaleController.class);
    @Autowired
    private MallAfterSaleService mallAfterSaleService;
    @PostMapping("createAfterSale")
    public boolean createAfterSale(@RequestBody MallAfterSaleAo mallAfterSaleAo, HttpServletRequest request)
    {
        LOGGER.error("MallAfterSaleAo:"+mallAfterSaleAo);
        mallAfterSaleAo.ckeck();

        int userId = TokenUtil.getUserId(request);
        return mallAfterSaleService.createAfterSale(mallAfterSaleAo,userId);
    }
    @GetMapping("getMyAfterSale")
    public List<MallAfterSaleVo> getMyAfterSale(HttpServletRequest request)
    {
        int userId = TokenUtil.getUserId(request);
        return mallAfterSaleService.getMyAfterSale(userId);
    }
    @GetMapping("getMyAfterSaleByPage")
    public List<MallAfterSaleVo> getMyAfterSaleByPage(int page,HttpServletRequest request)
    {
        int userId = TokenUtil.getUserId(request);
        return mallAfterSaleService.getMyAfterSale(userId,page);
    }
}

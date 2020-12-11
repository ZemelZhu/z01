package com.zemel.mallserver.controller.admin;

import com.zemel.data.type.Roles;
import com.zemel.framework.annotation.RolesAnnotation;
import com.zemel.mallserver.services.common.MallAfterSaleService;
import com.zemel.mallserver.type.OrderStatus;
import com.zemel.mallserver.vo.MallAfterSaleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/5/2 21:02
 */
@RolesAnnotation(roles = Roles.ADMIN)
@CrossOrigin
@RestController
@RequestMapping("admin/after/sale")
public class AdminAfterSaleController {
    @Autowired
    private MallAfterSaleService mallAfterSaleService;
    @GetMapping("getAfterSaleByPage")
    public List<MallAfterSaleVo> getOrdByPage(int page)
    {
        return mallAfterSaleService.getMallAfterSale(page);
    }
    @PostMapping("dealAfterSale")
    public boolean dealAfterSale(int id,boolean deal)
    {
        OrderStatus status = OrderStatus.REFUSE;
        if(deal)
            status =  OrderStatus.AGREE;
        return mallAfterSaleService.dealAfterSale(id,status);
    }
    @GetMapping("getAfterSaleById")
    public MallAfterSaleVo getAfterSaleById(long id)
    {
        return mallAfterSaleService.getAfterSaleById(id);
    }
    @GetMapping("getMallAllAfterSaleCount")
    public int getMallAllOrderCount()
    {
        return mallAfterSaleService.getMallAllOrderCount();
    }
}

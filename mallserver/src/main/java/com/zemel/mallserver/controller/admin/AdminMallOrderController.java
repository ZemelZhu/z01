package com.zemel.mallserver.controller.admin;

import com.zemel.data.type.Roles;
import com.zemel.framework.annotation.RolesAnnotation;
import com.zemel.mallserver.services.common.MallOrderService;
import com.zemel.mallserver.type.OrderStatus;
import com.zemel.mallserver.vo.MallOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/3/22 18:26
 */
@RolesAnnotation(roles = Roles.ADMIN)
@RestController
@CrossOrigin
@RequestMapping("admin/mallOrder")
public class AdminMallOrderController {
    @Autowired
    private MallOrderService mallOrderService;
    @GetMapping("getOrdByPage")
    public List<MallOrderVo> getOrdByPage(int page)
    {
        return mallOrderService.getOrdByPage(page);
    }
    @PostMapping("dealOrder")
    public boolean dealOrder(int id,boolean deal)
    {
        OrderStatus status = OrderStatus.REFUSE;
        if(deal)
            status =  OrderStatus.AGREE;
        return mallOrderService.dealOrder(id,status);
    }
    @GetMapping("getMallAllOrderCount")
    public int getMallAllOrderCount()
    {
        return mallOrderService.getMallAllOrderCount();
    }
    @GetMapping("getOrderById")
    public MallOrderVo getOrderById(long id)
    {
        return mallOrderService.getOrderById(id);
    }
}

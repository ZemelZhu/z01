package com.zemel.mallserver.controller.mall;

import com.zemel.framework.until.TokenUtil;
import com.zemel.mallserver.ao.OrderAo;
import com.zemel.mallserver.services.common.MallOrderService;
import com.zemel.mallserver.vo.MallOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/3/29 14:04
 */
@RestController
@CrossOrigin
@RequestMapping("mall/order")
public class MallOrderController {
    @Autowired
    private MallOrderService mallOrderService;

    @PostMapping("createMallOrder")
    public boolean createMallOrder(@RequestBody OrderAo order, HttpServletRequest request) {
        int userId = TokenUtil.getUserId(request);
        return mallOrderService.createMallOrder(order, userId);
    }
    @PostMapping("createMallOrder_new")
    public boolean createMallOrder_new(@RequestBody OrderAo order, HttpServletRequest request) {
        int userId = TokenUtil.getUserId(request);
        return mallOrderService.createMallOrder_new(order, userId);
    }
    @GetMapping("getMallOrder")
    public List<MallOrderVo> getMallOrder(HttpServletRequest request) {
        int userId = TokenUtil.getUserId(request);
        return mallOrderService.getMallOrder(userId);
    }
    @GetMapping("getMallOrderByPage")
    public List<MallOrderVo> getMallOrderByPage(HttpServletRequest request,int page) {
        int userId = TokenUtil.getUserId(request);
        return mallOrderService.getMallOrder(userId,page);
    }
}

package com.zemel.mallserver.services.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zemel.data.entiy.ProductOrderInfo;
import com.zemel.data.type.QueryWrapperUtil;
import com.zemel.data.util.PageUtil;
import com.zemel.framework.exception.TipException;
import com.zemel.mallserver.ao.OrderAo;
import com.zemel.mallserver.mapper.MallOrderInfoMapper;
import com.zemel.mallserver.type.OrderStatus;
import com.zemel.mallserver.vo.CommodityVo;
import com.zemel.mallserver.vo.MallOrderVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/3/22 18:29
 */
@Service
public class MallOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MallOrderService.class);
    @Resource
    private MallOrderInfoMapper mallOrderInfoMapper;
    @Autowired
    private CommodityService commodityService;

    public List<MallOrderVo> getOrdByPage(int page) {
        List<ProductOrderInfo> mallOrderInfoList = mallOrderInfoMapper.selectByPage(page * 10);
        return convert(mallOrderInfoList);
    }

    @Transactional
    public boolean createMallOrder(OrderAo order, int userId) {
        ProductOrderInfo productOrderInfo = null;
        if (!order.verify())
            return false;
        order.getProductId();
        CommodityVo commodity = commodityService.getCommodityById(order.getProductId());
        if(commodity==null)
            throw new TipException("找不到该产品");
        synchronized (commodity)
        {
            int count = commodity.getCount();
            if(count<order.getAmount())
                throw new TipException("产品剩余数量为:"+count+"不足"+order.getAmount());
            commodity.setCount(count-order.getAmount());
        }
        BigDecimal amount = new BigDecimal(order.getAmount());
            productOrderInfo = order.getProductOrderInfo();
            productOrderInfo.setCreateId(userId);
            if(commodity.getDiscountPrice().compareTo(BigDecimal.ZERO)!=0)
                productOrderInfo.setPayPrice(amount.multiply(commodity.getDiscountPrice()));
            else
                productOrderInfo.setPayPrice(amount.multiply(commodity.getPrice()));
            if (mallOrderInfoMapper.insert(productOrderInfo) > 0) ;
            LOGGER.error("create order:" + order);
            return true;
    }
    @Transactional
    public boolean createMallOrder_new(OrderAo order, int userId) {
        if (!order.verify_new())
            return false;
        ProductOrderInfo productOrderInfo = order.getProductOrderInfo();
        productOrderInfo.setCreateId(userId);
        mallOrderInfoMapper.insert(productOrderInfo);
        LOGGER.error("create order:" + productOrderInfo);
        return true;
    }
    private MallOrderVo convert(ProductOrderInfo info)
    {
        CommodityVo commodityById = commodityService.getCommodityById(info.getProductId());
        return new MallOrderVo(info, commodityById);
    }
    private List<MallOrderVo> convert(List<ProductOrderInfo> productOrderInfos) {
        if (productOrderInfos != null) {
            List<MallOrderVo> mallOrderVos = new ArrayList<>();
            for (ProductOrderInfo productOrderInfo : productOrderInfos) {
                    mallOrderVos.add(convert(productOrderInfo));
            }
            Collections.sort(mallOrderVos);
            return mallOrderVos;
        }
        return null;
    }

    public List<MallOrderVo> getMallOrder(int id) {
        List<ProductOrderInfo> productOrderInfos = mallOrderInfoMapper.selectByCreateId(id);
        return convert(productOrderInfos);
    }

    public boolean dealOrder(int id, OrderStatus status) {
        ProductOrderInfo productOrderInfo = mallOrderInfoMapper.selectById(id);
        if (productOrderInfo != null) {
            productOrderInfo.setOrderStatus(status.ordinal());
            productOrderInfo.setUpdateTime(new Date());
            mallOrderInfoMapper.updateById(productOrderInfo);
            return true;
        }
        return false;
    }

    public int getMallAllOrderCount() {
        return mallOrderInfoMapper.selectAllCount();
    }

    public List<MallOrderVo> getMallOrder(int userId, int page) {
        QueryWrapper<ProductOrderInfo> productOrderInfoQueryWrapper = QueryWrapperUtil.buildDataWrapper(ProductOrderInfo.class);
        productOrderInfoQueryWrapper.eq("create_id",userId);
        Page<ProductOrderInfo> productOrderInfoPage = mallOrderInfoMapper.selectPage(new Page<ProductOrderInfo>(page+1, PageUtil.PAGE_SIZE), productOrderInfoQueryWrapper);
        List<ProductOrderInfo> records = productOrderInfoPage.getRecords();
        return convert(records);
    }

    public MallOrderVo getOrderById(long id) {
        ProductOrderInfo productOrderInfo = mallOrderInfoMapper.selectById((int)id);
        if(productOrderInfo==null)
        {
            QueryWrapper<ProductOrderInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("transaction_id",id);
            productOrderInfo = mallOrderInfoMapper.selectOne(queryWrapper);
        }
        if(productOrderInfo==null)
                return null;
        return convert(productOrderInfo);
    }
}

package com.zemel.mallserver.vo;

import com.zemel.data.entiy.ProductOrderInfo;
import com.zemel.data.type.DataStatus;
import com.zemel.framework.ComponentManager;
import com.zemel.framework.until.TimerUtil;
import com.zemel.mallserver.component.FileComponent;
import com.zemel.mallserver.type.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/4/6 15:26
 */
@Data
public class MallOrderVo implements Comparable<MallOrderVo>{
    private int id;
    private CommodityVo commodityVo;
    private String address;
    private String phone;
    private String name;
    private String createTime;
    private String status;
    private String transactionId;
    private int createId;
    private int statusId;
    private int amount;
    private BigDecimal payPrice;
    private String payTime;
    private String dataStatus;
    private String desc;
    private List<String> picture;
    public MallOrderVo(ProductOrderInfo productOrderInfo)
    {
        this.id = productOrderInfo.getId();
        this.address =productOrderInfo.getAddress();
        this.phone = productOrderInfo.getPhone();
        this.name = productOrderInfo.getName();
        this.createTime = TimerUtil.date(productOrderInfo.getCreateTime());
        this.status = OrderStatus.getName(productOrderInfo.getOrderStatus());
        this.statusId = productOrderInfo.getOrderStatus();
        this.createId = productOrderInfo.getCreateId();
        this.transactionId = String.valueOf(productOrderInfo.getTransactionId());
        this.amount = productOrderInfo.getAmount();
        this.payPrice = productOrderInfo.getPayPrice();
        this.payTime = TimerUtil.date(productOrderInfo.getPayTime());
        this.dataStatus = DataStatus.parse(productOrderInfo.getStatus()).getValue();
        this.desc = productOrderInfo.getDescription();
        FileComponent component = ComponentManager.getInstance().getComponent(FileComponent.class);
        this.picture = component.getPictureUrl(productOrderInfo.getPicture());
    }

    public MallOrderVo(ProductOrderInfo productOrderInfo, CommodityVo commodityVo) {
        this(productOrderInfo);
        this.commodityVo = commodityVo;
    }

    @Override
    public int compareTo(MallOrderVo o) {
        return o.getCreateTime().compareTo(getCreateTime());
    }
}

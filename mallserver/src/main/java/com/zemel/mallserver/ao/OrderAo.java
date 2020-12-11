package com.zemel.mallserver.ao;

import com.zemel.data.entiy.ProductOrderInfo;
import com.zemel.data.type.DataStatus;
import com.zemel.framework.ComponentManager;
import com.zemel.framework.exception.TipException;
import com.zemel.framework.until.StringUtil;
import com.zemel.mallserver.type.OrderStatus;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zemel
 * @Date: 2020/4/4 23:42
 */
@Data
public class OrderAo {
    private int productId;
    private String address;
    private String phone;
    private String name;
    private int channelId;
    private int deviceId;
    private int amount;
    private String desc;
    private String picture;
    public boolean verify() {
        if(amount<=0||amount>99)
            throw new TipException("数量不能大于99,不能小于1");
        return verify_new();
    }
    public boolean verify_new() {
        if (StringUtil.isNullOrEmpty( phone, name))
            throw new TipException("部分信息不能为空");
        if ( name.length() > 200)
            throw new TipException("名字不能太长");
        if (!StringUtil.isValidPhoneNumber(phone))
            throw new TipException("请填写正确的手机号码");
        if(desc==null)
            desc = "";
        if(picture==null)
            picture = "";
        if(desc.length()>=500)
            throw new TipException("描述不能大于500");
        return true;
    }
    public ProductOrderInfo getProductOrderInfo() {
        ProductOrderInfo productOrderInfo = new ProductOrderInfo();
        productOrderInfo.setProductId(productId);
        productOrderInfo.setAddress(address);
        productOrderInfo.setPhone(phone);
        productOrderInfo.setName(name);
        productOrderInfo.setExpress("");
        productOrderInfo.setAmount(amount);
        productOrderInfo.setChannelId(1);
        productOrderInfo.setDevice(1);
        productOrderInfo.setMask(0);
        Date date = new Date();
        productOrderInfo.setCreateTime(date);
        productOrderInfo.setUpdateTime(date);
        productOrderInfo.setPayTime(new Date());
        productOrderInfo.setTransactionId(ComponentManager.getInstance().getUnionId());
        productOrderInfo.setStatus(DataStatus.NORMAL.ordinal());
        productOrderInfo.setOrderStatus(OrderStatus.CREATE.ordinal());
        productOrderInfo.setDescription(desc);
        productOrderInfo.setPicture(picture);
        return productOrderInfo;
    }

}

package com.zemel.mallserver.ao;

import com.zemel.data.type.DataStatus;
import com.zemel.framework.exception.TipException;
import com.zemel.framework.until.StringUtil;
import com.zemel.mallserver.entiy.CommodityInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: zemel
 * @Date: 2020/4/4 23:45
 */
@Data
public class CommodityAo {
    private int id;
    private String name;
    private int label;
    private int count;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String desc;
    private String picture;
    private String video;
    private String descPicture;

    public CommodityInfo tranInfo()  {
        price = new BigDecimal(0);
        discountPrice = new BigDecimal(0);
        if(StringUtil.isNullOrEmpty(name))
            throw new TipException("名字不能为空");
//        if(price.compareTo(BigDecimal.ZERO)<=0)
//            throw new TipException("价格不能小于或等于0");
//        if(discountPrice.compareTo(BigDecimal.ZERO)<=0)
//        throw new TipException("折扣价格不能小于或等于0");
        if(desc.length()>200)
            throw  new TipException("描述不能大于200个汉字");
        if(name.length()>20)
            throw  new TipException("名字不能大于20个汉字");
//        if(count<0)
//            throw new TipException("数量不能小于0");
        if(video==null)
            video = "";
        if(desc==null)
            desc = "";
        CommodityInfo commodityInfo = new CommodityInfo();
            commodityInfo.setBaseCount(count);
            commodityInfo.setCount(count);
        commodityInfo.setName(name);
        commodityInfo.setBaseCount(count);
        commodityInfo.setPrice(price);
        commodityInfo.setDiscountPrice(discountPrice);
        commodityInfo.setDescription(desc);
        commodityInfo.setLabel(label);
        commodityInfo.setStatus(DataStatus.NORMAL.ordinal());
        commodityInfo.setCreateTime(new Date());
        commodityInfo.setVideo(video);
        commodityInfo.setPicture(picture);
        commodityInfo.setDescPicture(descPicture);
        commodityInfo.setMask(0);
        return commodityInfo;
    }
}

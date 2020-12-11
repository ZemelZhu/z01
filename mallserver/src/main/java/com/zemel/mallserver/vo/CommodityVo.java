package com.zemel.mallserver.vo;

import com.zemel.data.type.DataStatus;
import com.zemel.framework.ComponentManager;
import com.zemel.mallserver.component.FileComponent;
import com.zemel.mallserver.entiy.CommodityInfo;
import com.zemel.mallserver.type.QQVidelUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/4/4 23:36
 */
@Data
public class CommodityVo {
    private int id;
    private String name;
    private String label;
    private int count;
    private int baseCount;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String desc;
    private List<String> picture;
    private List<String> descPicture;
    private String video;
    private int isHomeCarousel;
    private String videoCode;
    private String dataStatus;
    public enum MaskStatus {
        IS_HOME_CAROUSEL,
    }

    public CommodityVo() {
        super();
    }

    public CommodityVo(CommodityInfo commodityInfo, MallLabelVo mallLabelVo) {
        this.id = commodityInfo.getId();
        this.name = commodityInfo.getName();
        if(mallLabelVo!=null)
            this.label = mallLabelVo.getLabelName();
        else
            this.label = "";
        this.count = commodityInfo.getCount();
        this.baseCount = commodityInfo.getBaseCount();
        this.price = commodityInfo.getPrice();
        this.discountPrice = commodityInfo.getDiscountPrice();
        this.desc = commodityInfo.getDescription();
        this.video = commodityInfo.getVideo();
        int mask = 1 << MaskStatus.IS_HOME_CAROUSEL.ordinal();
        this.isHomeCarousel = (commodityInfo.getMask() & mask) == mask ? 1 : 0;
        this.videoCode = QQVidelUtil.getCode(this.video);
        FileComponent component = ComponentManager.getInstance().getComponent(FileComponent.class);
        this.picture = component.getPictureUrl(commodityInfo.getPicture());
        this.descPicture = component.getPictureUrl(commodityInfo.getDescPicture());
        this.dataStatus = DataStatus.parse(commodityInfo.getStatus()).getValue();
    }



}

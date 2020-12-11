package com.zemel.mallserver.vo;

import com.zemel.data.type.DataStatus;
import com.zemel.framework.ComponentManager;
import com.zemel.framework.until.TimerUtil;
import com.zemel.mallserver.component.FileComponent;
import com.zemel.mallserver.entiy.MallAfterSaleInfo;
import com.zemel.mallserver.type.AfterSaleType;
import com.zemel.mallserver.type.OrderStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/5/2 17:32
 */
@Data
public class MallAfterSaleVo  implements Comparable<MallAfterSaleVo> {
    private int id;
    private int createId;
    private int type;
    private String userName;
    private String phone;
    private String address;
    private List<String> picture;
    private String desc;
    private String supperTime;
    private MallLabelVo mallLabelVo;
    private CommodityVo commodityVo;
    private ExpressVo expressVo;
    private int serviceStatus;
    private String createTime;
    private String transactionId;
    private String status;
    private String typeStr;
    private String dataStatus;
    private int statusId;
    public MallAfterSaleVo(MallAfterSaleInfo info, CommodityVo commodityVo, MallLabelVo labelVo) {
        this.id = info.getId();
        this.commodityVo = commodityVo;
        this.mallLabelVo = labelVo;
        this.userName = info.getUserName();
        this.phone = info.getPhone();
        this.address = info.getAddress();
        this.supperTime = TimerUtil.date(info.getSupperTime());
        this.serviceStatus = info.getServiceStatus();
        this.createTime = TimerUtil.date(info.getCreateTime());
        this.type = info.getType();
        this.createId = info.getCreateId();
        this.desc = info.getDescription();
        this.transactionId = String.valueOf(info.getTransactionId());
        this.status = OrderStatus.getName(info.getServiceStatus());
        this.statusId = info.getServiceStatus();
        FileComponent component = ComponentManager.getInstance().getComponent(FileComponent.class);
        this.picture = component.getPictureUrl(info.getPicture());
        this.typeStr = AfterSaleType.parse(this.type).getValue();
        this.dataStatus = DataStatus.parse(info.getStatus()).getValue();
    }
    private List<String> getPictureList(String fileDownload,String pic)
    {
        String[] split = pic.split(",");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if(!split[i].equals(""))
            {
                String pictureUrl = fileDownload + split[i];
                list.add(pictureUrl);
            }

        }
        return list;
    }

    @Override
    public int compareTo(MallAfterSaleVo o) {
        return o.getCreateTime().compareTo(getCreateTime());
    }
}

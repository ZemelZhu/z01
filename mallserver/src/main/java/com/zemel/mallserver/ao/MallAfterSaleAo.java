package com.zemel.mallserver.ao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zemel.data.type.DataStatus;
import com.zemel.framework.ComponentManager;
import com.zemel.framework.exception.TipException;
import com.zemel.framework.until.StringUtil;
import com.zemel.mallserver.component.FileComponent;
import com.zemel.mallserver.entiy.MallAfterSaleInfo;
import com.zemel.mallserver.type.AfterSaleType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/5/2 20:58
 */
@Data
public class MallAfterSaleAo {
    private static final long TIME = 30*60*1000;
    private int type;
    private String userName;
    private String phone;
    private String address;
    private String picture;
    private String desc;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date supperTime;
    private int labelId;
    public void ckeck() {
        if(StringUtil.isNullOrEmpty(userName))
            throw new TipException("名字不能为空");
        if(StringUtil.isNullOrEmpty(address))
            throw new TipException("地址不能为空");
        if(!StringUtil.isValidPhoneNumber(phone))
            throw new TipException("电话号码不合法");
        if(userName.length()>20||address.length()>200)
            throw new TipException("名字或者描述过长");
        AfterSaleType parse = AfterSaleType.parse(type);
        if(parse==null)
            throw new TipException("找不到该售后类型");
        if(parse==AfterSaleType.SERVICE)
        {
            if(StringUtil.isNullOrEmpty(desc))
                throw new TipException("描述不能为空");
            if(supperTime==null)
                throw new TipException("请填写服务时间");
            if(supperTime.getTime()<System.currentTimeMillis()+TIME)
                throw new TipException("填写的服务时间必须大于30分钟之后");
        }
        else
        {
            if(supperTime==null)
                supperTime = new Date();
            if(desc==null)
                desc = "";
        }
        if(!StringUtil.isNullOrEmpty(picture))
        {
            try {

                FileComponent component = ComponentManager.getInstance().getComponent(FileComponent.class);
                String[] split = picture.split(",");
                List<String> list = new ArrayList<>();
                String fileDir = component.getFileDir();
                for (int i = 0; i < split.length; i++) {
                    if(!split[i].equals(""))
                    {
                        String pictureUrl = split[i];
                        File file = new File(fileDir + pictureUrl);
                        if(!file.exists())
                            throw new TipException("找不到图片");
                    }

                }

            }catch (Exception e)
            {
                if(e instanceof TipException)
                    e.printStackTrace();
                throw new TipException("图片异常");
            }
        }
    }
    public MallAfterSaleInfo transform() {
        MallAfterSaleInfo mallAfterSaleInfo = new MallAfterSaleInfo();
        mallAfterSaleInfo.setType(type);
        mallAfterSaleInfo.setUserName(userName);
        mallAfterSaleInfo.setPhone(phone);
        mallAfterSaleInfo.setAddress(address);
        mallAfterSaleInfo.setDescription(desc);
        mallAfterSaleInfo.setSupperTime(supperTime);
        mallAfterSaleInfo.setCreateTime(new Date());
        mallAfterSaleInfo.setUpdateTime(new Date());
        mallAfterSaleInfo.setStatus(DataStatus.NORMAL.ordinal());
        mallAfterSaleInfo.setPicture(picture);
        mallAfterSaleInfo.setExpress("");
        mallAfterSaleInfo.setProductId(0);
        mallAfterSaleInfo.setProductType(labelId);
        mallAfterSaleInfo.setServiceStatus(0);
        mallAfterSaleInfo.setTransactionId(ComponentManager.getInstance().getUnionId());
        return mallAfterSaleInfo;
    }
}

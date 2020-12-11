package com.zemel.mallserver.vo;

import com.zemel.framework.until.TimerUtil;
import com.zemel.mallserver.entiy.MallUserInfo;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/4/6 17:03
 */
@Data
public class MallUserInfoVo {

    private int id;

    private String nickName;

    private String phone;

    private String province;

    private String city;

    private String country;

    private String address;

    private String createTime;

    public MallUserInfoVo(MallUserInfo mallUserInfo) {
        this.id = mallUserInfo.getId();
        this.nickName = mallUserInfo.getNickName();
        this.phone = mallUserInfo.getPhone();
        this.province = mallUserInfo.getProvince();
        this.city = mallUserInfo.getCity();
        this.country = mallUserInfo.getCountry();
        this.address = mallUserInfo.getAddress();
        this.createTime = TimerUtil.date(mallUserInfo.getCreateTime());
    }
}

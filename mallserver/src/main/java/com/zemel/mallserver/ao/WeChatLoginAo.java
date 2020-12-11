package com.zemel.mallserver.ao;

import com.zemel.data.type.Gender;
import com.zemel.mallserver.entiy.MallUserInfo;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zemel
 * @Date: 2020/4/4 23:38
 */
@Data
public class WeChatLoginAo {
    private String code;
    private String nickName;
    private String avatarUrl;
    private int gender;
    private String province;
    private String city;
    private String country;
    public void updateMallUserInfo(MallUserInfo mallUserInfo)
    {
        mallUserInfo.setNickName(nickName);
        mallUserInfo.setAvatarUrl(avatarUrl);
        mallUserInfo.setGender(gender== Gender.MAN.ordinal());
        mallUserInfo.setProvince(province);
        mallUserInfo.setCity(city);
        mallUserInfo.setCountry(country);
        mallUserInfo.setUpdateTime(new Date());
    }
}

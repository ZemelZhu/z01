package com.zemel.mallserver.ao;

import com.zemel.framework.exception.TipException;
import com.zemel.framework.until.StringUtil;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/5/2 10:19
 */
@Data
public class OfficialLinkAo {
    private int id;
    private String name;
    private String phone;
    private String address;
    private String mail;
    private String desc;
    public void check()
    {
        if(StringUtil.isNullOrEmpty(name,address,desc))
            throw new TipException("字段不能为空");
        if(name.length()>50)
            throw new TipException("名字不能大于50个汉字");
        if(desc.length()>200)
            throw new TipException("描述不能大于200个汉字");
        if(!StringUtil.isValidPhoneNumber(phone))
            throw new TipException("电话号码有问题");
        if(!StringUtil.isEmail(mail))
            throw new TipException("邮件地址有问题");

    }
}

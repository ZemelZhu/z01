package com.zemel.web1.controller;

import com.zemel.framework.ComponentManager;
import com.zemel.web_framework.component.FileComponent;
import com.zemel.web_framework.config.FileConfig;
import com.zemel.web_framework.utils.QRCodeUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zemel
 * @Date: 2020/8/2 22:26
 */
@RestController
@Api(tags = "二维码相关")
@RequestMapping("qr")
public class QRController {
    @Autowired
    private FileConfig fileConfig;
    @PostMapping("buildQR")
    public String buildQR(String address)
    {
        String fileDir = fileConfig.getFileDir();
        String destPath = fileDir;
        String name = ComponentManager.getInstance().getUnionId()+".jpg";
        try {
            QRCodeUtils.encode0(address, destPath, name);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ComponentManager.getInstance().getComponent(FileComponent.class).getPicturePath()+name;
    }
}

package com.zemel.mallserver.controller.common;

import com.zemel.mallserver.component.FileComponent;
import com.zemel.mallserver.services.common.QiNiuYunService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @Author: zemel
 * @Date: 2020/8/18 20:14
 */
@RestController
@RequestMapping("/common/mallSystem")
public class MallSystemController {
    @Autowired
    private FileComponent fileComponent;
    @GetMapping("excute")
    public boolean excute(String key)
    {
        if(key.equals("haha"))
        {
            File file = new File("/mallserver/config/static/image");
            File[] files = file.listFiles();
            if(files!=null)
            {
                for(File f:files)
                {
                    System.out.println(f.getName());
                QiNiuYunService1.upload(f,f.getName());
                }
                return true;
            }
        }
        return false;
    }
}

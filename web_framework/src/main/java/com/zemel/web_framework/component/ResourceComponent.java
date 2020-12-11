package com.zemel.web_framework.component;

import com.zemel.framework.component.IComponent;
import com.zemel.framework.timer.IDailyTimer;
import com.zemel.web_framework.model.ResourceBean;
import com.zemel.web_framework.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zemel
 * @Date: 2020/7/19 22:01
 */
@Component
public class ResourceComponent implements IComponent , IDailyTimer {
    private static final Logger logger = LoggerFactory.getLogger(ResourceComponent.class);
    private Map<String,ResourceBean> guestResource = new ConcurrentHashMap<>();
    @Autowired
    private FileComponent fileComponent;

    public void addResource(String resource)
    {
        String username = SecurityUtils.getUsername();
        ResourceBean resourceBean = new ResourceBean(resource, username);
        guestResource.put(resource,resourceBean);
    }

    public void confirmResource(String resource)
    {
        guestResource.remove(resource);
    }
    @Override
    public void dailyJob() {

        ArrayList<ResourceBean> resourceBeans = new ArrayList<>();
        long limit = 6*60*60*1000;
        long time = System.currentTimeMillis();
        for(ResourceBean bean:guestResource.values())
        {
            if(time-bean.getCreateTime()>limit)
            {
                resourceBeans.add(bean);
                guestResource.remove(bean);
            }
        }
        String fileDir = fileComponent.getFileDir();
        for(ResourceBean bean:resourceBeans)
        {
            String name = fileDir+bean.getResource();
            File file = new File(name);
            if(file.exists()&&file.isFile())
            {
                file.delete();
                logger.error("delete resource"+bean);
            }
        }
    }

    public void confirmResource(List<String> resource) {
        resource.forEach(f->{
            guestResource.remove(f);
        });
    }

    @Override
    public boolean initialize() {
        return true;
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public void stop() {
        String fileDir = fileComponent.getFileDir();
        for(ResourceBean bean:guestResource.values())
        {
            String name = fileDir+bean.getResource();
            File file = new File(name);
            if(file.exists()&&file.isFile())
            {
                file.delete();
                logger.error("delete resource"+bean);
            }
        }
    }

    @Override
    public boolean reload() {
        return false;
    }
}

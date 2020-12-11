package com.zemel.web1.vo;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.until.StringUtil;
import com.zemel.web1.entiy.ResourceInfo;
import com.zemel.web_framework.component.FileComponent;
import com.zemel.web_framework.vo.BaseVo;
import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/7/21 22:45
 */
@Data
public class ResourceInfoVo implements BaseVo {
    private int id;
    private String resource;
    private String resourceName;
    private int userId;
    private String name;
    private int projectId;
    public ResourceInfoVo(ResourceInfo resourceInfo) {
        this.id = resourceInfo.getId();
        if (!StringUtil.isNullOrEmpty(resourceInfo.getResource())) {
            this.resourceName = resourceInfo.getResource();
            this.resource = ComponentManager.getInstance().getComponent(FileComponent.class).getPicturePath() + resourceInfo.getResource();
        }
        this.userId = resourceInfo.getUserId();
        this.name = resourceInfo.getName();
        this.projectId = resourceInfo.getProjectId();
    }
}

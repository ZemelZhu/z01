package com.zemel.web1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zemel.data.type.DataStatus;
import com.zemel.data.type.QueryWrapperUtil;
import com.zemel.framework.exception.TipException;
import com.zemel.framework.vo.ResponseVo;
import com.zemel.web1.ao.ResourceAo;
import com.zemel.web1.entiy.ResourceInfo;
import com.zemel.web1.mapper.ProjectInfoMapper;
import com.zemel.web1.mapper.ResourceInfoMapper;
import com.zemel.web1.service.DiyService;
import com.zemel.web1.vo.ProjectVo;
import com.zemel.web1.vo.ResourceInfoVo;
import com.zemel.web_framework.component.ResourceComponent;
import com.zemel.web_framework.utils.SecurityUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zemel
 * @Date: 2020/7/20 22:12
 */
@RestController
@Api(tags = "资源管理相关接口")
@RequestMapping("resource")
public class ResourceController {
    @Resource
    private ResourceInfoMapper resourceInfoMapper;
    @Resource
    private ProjectInfoMapper projectInfoMapper;
    @Autowired
    private DiyService diyService;
    @Autowired
    private ResourceComponent resourceComponent;

    @PostMapping("addResource")
    public ResourceInfoVo addResource(ResourceAo resourceAo)
    {
        resourceAo.check();

        int userId = SecurityUtils.getUserId();
        ProjectVo projectById = diyService.getProjectById(resourceAo.getProjectId());
        if(projectById.getUserId()!=userId)
            throw new TipException("这不是你的项目");
        ResourceInfo resourceInfo = new ResourceInfo();
        resourceInfo.setCreateTime(new Date());
        resourceInfo.setUpdateTime(new Date());
        resourceInfo.setResource(resourceAo.getResource());
        resourceInfo.setName(resourceAo.getName());
        resourceInfo.setUserId(userId);
        resourceInfo.setProjectId(resourceAo.getProjectId());
        resourceInfo.setStatus(DataStatus.NORMAL.ordinal());
        resourceInfo.setType(resourceAo.getType());
        resourceInfoMapper.insert(resourceInfo);
        resourceComponent.confirmResource(resourceAo.getResource());
        return new ResourceInfoVo(resourceInfo);
    }
    @DeleteMapping("deleteResource")
    public boolean deleteResource(int id)
    {
        int userId = SecurityUtils.getUserId();
        ResourceInfo resourceInfo =getResourceInfo(id);
        if(resourceInfo.getUserId()!=userId)
            throw new TipException("这不是你的项目");
        resourceInfo.setStatus(DataStatus.DELETE.ordinal());
        resourceInfo.setUpdateTime(new Date());
        resourceInfoMapper.updateById(resourceInfo);
        return true;
    }
    private ResourceInfo getResourceInfo(int id)
    {
        ResourceInfo resourceInfo = resourceInfoMapper.selectById(id);
        if(resourceInfo==null)
            throw new TipException("找不到资源");
        int userId = SecurityUtils.getUserId();
        if(resourceInfo.getUserId()!=userId&&resourceInfo.getUserId()!=0)
            throw new TipException("不是你的资源");
        return resourceInfo;
    }
    @GetMapping("getResource")
    public ResourceInfoVo getResource(int id)
    {
        ResourceInfo resourceInfo = getResourceInfo(id);
       return new ResourceInfoVo(resourceInfo);
    }
    @GetMapping("getResourceByType")
    public ResponseVo getResourceByType(int type,int projectId)
    {
        int userId = SecurityUtils.getUserId();
        QueryWrapper<ResourceInfo> resourceInfoQueryWrapper = QueryWrapperUtil.buildDataWrapper(ResourceInfo.class);
        resourceInfoQueryWrapper.eq("type",type).eq("user_id",userId).eq("project_id",projectId).or().eq("user_id",0).eq("type",type);
        List<ResourceInfo> resourceInfos1 = resourceInfoMapper.selectList(resourceInfoQueryWrapper);
        List<ResourceInfo> resourceInfos = resourceInfos1;
        List<ResourceInfoVo> collect = resourceInfos.stream().map(f -> new ResourceInfoVo(f)).collect(Collectors.toList());
        return ResponseVo.build(collect);
    }
    @GetMapping("getAllResource")
    public ResponseVo getAllResource()
    {
        int userId = SecurityUtils.getUserId();
       return   ResponseVo.build(diyService.getAllResource(userId));
    }
}

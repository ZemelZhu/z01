package com.zemel.web1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zemel.data.type.DataStatus;
import com.zemel.data.type.QueryWrapperUtil;
import com.zemel.framework.exception.TipException;
import com.zemel.framework.vo.ResponseVo;
import com.zemel.web1.ao.ProjectAo;
import com.zemel.web1.entiy.ProjectInfo;
import com.zemel.web1.mapper.ProjectInfoMapper;
import com.zemel.web1.service.DiyService;
import com.zemel.web1.vo.PageVo;
import com.zemel.web1.vo.ProjectVo;
import com.zemel.web_framework.utils.SecurityUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/7/20 22:40
 */
@RestController
@Api(tags = "项目管理相关接口")
@RequestMapping("project")
public class ProjectController {
    @Autowired
    private DiyService diyService;
    @Resource
    private ProjectInfoMapper projectInfoMapper;

    @PostMapping("addProject")
    public ProjectVo addProject(ProjectAo projectAo) {
        projectAo.check();
        int userId = SecurityUtils.getUserId();
        ProjectInfo projectInfo = new ProjectInfo();
        projectAo.init(projectInfo);
        projectInfo.setUserId(userId);
        projectInfo.setStatus(DataStatus.NORMAL.ordinal());
        projectInfo.setCreateTime(new Date());
        projectInfo.setUpdateTime(new Date());
        projectInfoMapper.insert(projectInfo);
        return new ProjectVo(projectInfo);
    }

    private ProjectInfo getProjectInfo(int projectId) {
        ProjectInfo projectInfo = projectInfoMapper.selectById(projectId);
        if (projectInfo == null || projectInfo.getStatus() != DataStatus.NORMAL.ordinal())
            throw new TipException("找不到项目信息");
        int userId = SecurityUtils.getUserId();
        if (projectInfo.getUserId() != userId)
            throw new TipException("这不是你的项目哦");
        return projectInfo;
    }

    @PostMapping("modifyProject")
    public ProjectVo modifyProject(ProjectAo projectAo) {
        ProjectInfo projectInfo = getProjectInfo(projectAo.getId());
        projectAo.init(projectInfo);
        projectInfo.setUpdateTime(new Date());
        projectInfoMapper.updateById(projectInfo);
        return diyService.getProject(projectAo.getId(), projectInfo);
    }

    @DeleteMapping("deleteProject")
    public boolean deleteProject(int projectId) {
        ProjectInfo projectInfo = getProjectInfo(projectId);
        projectInfo.setStatus(DataStatus.DELETE.ordinal());
        projectInfo.setUpdateTime(new Date());
        projectInfoMapper.updateById(projectInfo);
        return true;

    }

    @GetMapping("getProject")
    public ProjectVo getProject(int projectId) {

        return diyService.getProjectById(projectId);
    }

    @GetMapping("getProjectName")
    public PageVo getProjectName(String projectName, int page) {
        List<ProjectVo> projectName1 = diyService.getProjectName(projectName, page);
        QueryWrapper<ProjectInfo> projectInfoQueryWrapper = QueryWrapperUtil.buildDataWrapper(ProjectInfo.class);
        projectInfoQueryWrapper.like("project_name", projectName);
        Integer count = projectInfoMapper.selectCount(projectInfoQueryWrapper);
        PageVo pageVo = new PageVo(page, projectName1, count);
        return pageVo;
    }

    @GetMapping("getProjectByPage")
    public PageVo getProjectByPage(int page) {
        int userId = SecurityUtils.getUserId();
        List<?> projectByPage = diyService.getProjectByPage(userId, page);
        PageVo pageVo = new PageVo(page, projectByPage, diyService.getProjectCount(userId));
        return pageVo;
    }

    @GetMapping("getAllProject")
    public ResponseVo getAllProject() {
        int userId = SecurityUtils.getUserId();
        List<ProjectVo> allProject = diyService.getAllProject(userId);
        return ResponseVo.build(allProject);
    }
}

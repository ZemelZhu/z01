package com.zemel.web1.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zemel.data.type.DataStatus;
import com.zemel.data.type.QueryWrapperUtil;
import com.zemel.framework.exception.TipException;
import com.zemel.framework.until.SerializeUtil;
import com.zemel.web1.dto.DiyDto;
import com.zemel.web1.entiy.PlayerDiyInfo;
import com.zemel.web1.entiy.ProjectInfo;
import com.zemel.web1.entiy.ResourceInfo;
import com.zemel.web1.entiy.model.PersistPlayerDiyInfo;
import com.zemel.web1.mapper.PlayerDiyInfoMapper;
import com.zemel.web1.mapper.ProjectInfoMapper;
import com.zemel.web1.mapper.ResourceInfoMapper;
import com.zemel.web1.vo.DiyVo;
import com.zemel.web1.vo.ProjectVo;
import com.zemel.web1.vo.ResourceInfoVo;
import com.zemel.web_framework.component.FileComponent;
import com.zemel.web_framework.component.ResourceComponent;
import com.zemel.web_framework.config.FileConfig;
import com.zemel.web_framework.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zemel
 * @Date: 2020/7/20 22:28
 */
@Service
public class DiyService {
    @Resource
    private ResourceInfoMapper resourceInfoMapper;
    @Resource
    private ProjectInfoMapper projectInfoMapper;
    @Resource
    private PlayerDiyInfoMapper playerDiyInfoMapper;
    @Autowired
    private FileComponent fileComponent;
    @Autowired
    private FileConfig fileConfig;
    @Autowired
    private ResourceComponent resourceComponent;

    public ProjectVo getProjectById(int projectId)
    {
        ProjectInfo projectInfo = projectInfoMapper.selectById(projectId);
        if(projectInfo==null||projectInfo.getStatus()!= DataStatus.NORMAL.ordinal())
            throw new TipException("找不到项目信息");
        return getProject(projectId,projectInfo);
    }
    public ProjectVo getProject(int projectId,ProjectInfo projectInfo)
    {
        ProjectVo projectVo = new ProjectVo(projectInfo);
        List<PlayerDiyInfo> playerDiyInfos = playerDiyInfoMapper.selectAllByProjectId(projectId);
        List<DiyVo> diyVos = new ArrayList<>();
        projectVo.setPageList(diyVos);
        for(PlayerDiyInfo info:playerDiyInfos)
        {
            if(info.getDiyMessage()!=null) {
                DiyDto dto = SerializeUtil.deserialize(info.getDiyMessage(), DiyDto.class);
                if(dto!=null) {
                    DiyVo diyVo = dto.toVo(info);
                    diyVos.add(diyVo);
                }
            }
        }
        return projectVo;
    }
    public PersistPlayerDiyInfo getDiy(int diyId)
    {
        int userId = SecurityUtils.getUserId();
        PersistPlayerDiyInfo playerDiyInfo = playerDiyInfoMapper.selectById(diyId);
        if (playerDiyInfo == null)
            throw new TipException("找不到信息");
        if (playerDiyInfo.getUserId() != userId)
            throw new TipException("你不能编辑这个");
        return playerDiyInfo;
    }
    public DiyVo getDiyById(int diyId)
    {
        PlayerDiyInfo playerDiyInfo = playerDiyInfoMapper.selectById(diyId);
        if (playerDiyInfo == null)
            throw new TipException("找不到信息");
        DiyDto dto = null;
        if(playerDiyInfo.getDiyMessage()!=null)
            dto = SerializeUtil.deserialize(playerDiyInfo.getDiyMessage(), DiyDto.class);
        else
            dto = new DiyDto();
        DiyVo diyVo = dto.toVo(playerDiyInfo);
        return diyVo;
    }

    public List<ProjectVo> getAllProject(int userId) {
        List<ProjectInfo> projectInfos = projectInfoMapper.selectAllByUserId(userId);
        List<ProjectVo> projectVos = new ArrayList<>();
        projectInfos.forEach(f->{
            projectVos.add(getProject(f.getId(),f));
        });
        return projectVos;
    }

    public List<ResourceInfoVo> getAllResource(int userId) {

        List<ResourceInfo> resourceInfos = resourceInfoMapper.selectAllByUserId(userId);

        return resourceInfos.stream().map(f->new ResourceInfoVo(f)).collect(Collectors.toList());
    }
    public int getProjectCount(int userId)
    {
        QueryWrapper<ProjectInfo> projectInfoQueryWrapper = QueryWrapperUtil.buildDataWrapper(ProjectInfo.class);
        projectInfoQueryWrapper.eq("user_id",userId);
        return projectInfoMapper.selectCount(projectInfoQueryWrapper);
    }
    public List<ProjectVo> getProjectByPage(int userId, int page) {
        QueryWrapper<ProjectInfo> projectInfoQueryWrapper = QueryWrapperUtil.buildDataWrapper(ProjectInfo.class);
        projectInfoQueryWrapper.eq("user_id",userId);
        Page<ProjectInfo> projectInfoPage = projectInfoMapper.selectPage(new Page<ProjectInfo>(page + 1, 5), projectInfoQueryWrapper);
        List<ProjectInfo> records = projectInfoPage.getRecords();
        List<ProjectVo> projectVos = new ArrayList<>();
        records.forEach(f->{
            projectVos.add(new ProjectVo(f));
        });
        return projectVos;
    }

    public List<ProjectVo> getProjectName(String projectName, int page) {
        QueryWrapper<ProjectInfo> projectInfoQueryWrapper = QueryWrapperUtil.buildDataWrapper(ProjectInfo.class);
        projectInfoQueryWrapper.like("project_name",projectName);
        Page<ProjectInfo> projectInfoPage = projectInfoMapper.selectPage(new Page<ProjectInfo>(page + 1, 5), projectInfoQueryWrapper);
        List<ProjectInfo> projectInfos = projectInfoPage.getRecords();
        if(projectInfos==null||projectInfos.size()==0)
            throw new TipException("找不到项目信息");
        List<ProjectVo> projectVos = new ArrayList<>();
        projectInfos.forEach(f->{
            projectVos.add(new ProjectVo(f));
        });
        return projectVos;
    }
}

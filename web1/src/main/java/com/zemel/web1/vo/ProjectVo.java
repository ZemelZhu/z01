package com.zemel.web1.vo;

import com.zemel.framework.until.TimerUtil;
import com.zemel.web1.entiy.ProjectInfo;
import com.zemel.web_framework.vo.BaseVo;
import lombok.Data;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/7/20 22:30
 */
@Data
public class ProjectVo implements BaseVo {
    private int projectId;
    private String projectName;
    private int userId;
    private String desc;
    private String beginLimitTime;
    private String endLimitTime;
    private List<DiyVo> pageList;

    public ProjectVo(ProjectInfo projectInfo) {
        this.projectId = projectInfo.getId();
        this.projectName = projectInfo.getProjectName();
        this.userId = projectInfo.getUserId();
        this.desc = projectInfo.getProjectDesc();
        this.beginLimitTime =  TimerUtil.date1(projectInfo.getBeginLimitTime());
        this.endLimitTime =  TimerUtil.date1(projectInfo.getEndLimitTime());
    }
}

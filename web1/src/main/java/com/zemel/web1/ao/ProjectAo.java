package com.zemel.web1.ao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zemel.framework.exception.TipException;
import com.zemel.framework.until.StringUtil;
import com.zemel.web1.entiy.ProjectInfo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: zemel
 * @Date: 2020/7/27 21:07
 */
@Data
public class ProjectAo {
    private int id;
    private String projectName;
    private String desc;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginLimitTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endLimitTime;
    public void check() {
        if(StringUtil.isNullOrEmpty(projectName,desc))
            throw new TipException("名字或描述不能为空");
        if(beginLimitTime==null)
            throw new TipException("开始时间不能为空");
        if(endLimitTime==null)
            throw new TipException("结束时间不能为空");
    }

    public void init(ProjectInfo projectInfo) {
        projectInfo.setProjectName(getProjectName());
        projectInfo.setProjectDesc(getDesc());
        projectInfo.setBeginLimitTime(getBeginLimitTime());
        projectInfo.setEndLimitTime(getEndLimitTime());
    }
}

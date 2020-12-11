package com.zemel.web1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zemel.web1.entiy.ProjectInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/7/20 22:27
 */
public interface ProjectInfoMapper  extends BaseMapper<ProjectInfo> {
    @Select("select * from core_project where user_id = #{userId} and status=0")
    List<ProjectInfo> selectAllByUserId(int userId);
    @Select("select * from core_project where project_name like #{projectName} and status=0")
    List<ProjectInfo> selectByProjectName(String projectName);
}

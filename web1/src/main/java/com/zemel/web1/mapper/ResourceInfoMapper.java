package com.zemel.web1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zemel.web1.entiy.ResourceInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/7/20 22:22
 */
public interface ResourceInfoMapper extends BaseMapper<ResourceInfo> {
    @Select("select * from core_resource where user_id = #{userId} and status=0")
    List<ResourceInfo> selectAllByUserId(int userId);
}

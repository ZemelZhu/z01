package com.zemel.mallserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zemel.mallserver.entiy.AdminUserInfo;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: zemel
 * @Date: 2020/3/21 15:52
 */
public interface AdminUserInfoMapper extends BaseMapper<AdminUserInfo> {
    @Select("select * from mall_admin_user where name = #{name}")
    AdminUserInfo selectByName(String name);
}

package com.zemel.web_framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zemel.data.entiy.UserInfo;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: zemel
 * @Date: 2020/7/18 10:58
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
       @Select("select * from common_user where user_name = #{userName} and status=0")
        public UserInfo getUserInfoByName(String userName);
    @Select("select * from common_user where token = #{token} and status=0")
    public UserInfo getUserInfoByToken(String token);
}

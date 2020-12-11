package com.zemel.mallserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zemel.mallserver.entiy.MallUserInfo;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: zemel
 * @Date: 2020/3/22 22:44
 */
public interface MallUserInfoMapper extends BaseMapper<MallUserInfo> {
    @Select("select * from mall_mall_user where union_id = #{openid}")
    MallUserInfo selectByUnionId(String openid);
}

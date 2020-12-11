package com.zemel.mallserver.services.mall;

import com.zemel.mallserver.entiy.MallUserInfo;
import com.zemel.mallserver.mapper.MallUserInfoMapper;
import com.zemel.mallserver.vo.MallUserInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: zemel
 * @Date: 2020/3/22 22:31
 */
@Service
public class MallUserService {
    @Resource
    private MallUserInfoMapper mallUserInfoMapper;
    public MallUserInfoVo getInfoById(int id) {
        MallUserInfo mallUserInfo = mallUserInfoMapper.selectById(id);
        if(mallUserInfo!=null)
        {
            return new MallUserInfoVo(mallUserInfo);
        }
        return null;
    }
}

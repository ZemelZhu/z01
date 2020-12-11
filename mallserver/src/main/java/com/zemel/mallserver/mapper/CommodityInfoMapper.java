package com.zemel.mallserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zemel.mallserver.entiy.CommodityInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/3/22 11:44
 */
//@CacheNamespace(implementation= EhcacheCache.class,eviction=EhcacheCache.class)
public interface CommodityInfoMapper extends BaseMapper<CommodityInfo> {
    @Select("select count(id) from mall_commodity where status=0")
    int selectAllCount();
    @Select("select * from mall_commodity where status=0 and mask& #{mask}>0")
    List<CommodityInfo> selectMask(int mask);
}

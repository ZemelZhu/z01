package com.zemel.mallserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zemel.mallserver.entiy.MallLabelInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zemel
 *
 *
 *
 *
 * @Date: 2020/4/5 11:42
 */
//@CacheNamespace(implementation= MyCache.class,eviction=MyCache.class)
public interface MallLabelInfoMapper  extends BaseMapper<MallLabelInfo> {
    @Select("select * from mall_mall_label where status = #{status}")
    List<MallLabelInfo> selectAllLabel(int status);
}

package com.zemel.mallserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zemel.mallserver.entiy.MallAfterSaleInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/5/2 17:21
 */
public interface MallAfterSaleInfoMapper  extends BaseMapper<MallAfterSaleInfo> {
    @Select("select * from mall_mall_after_sale where status=0 limit #{index},10")
    List<MallAfterSaleInfo> selectByPage(int index);
    @Select("select * from mall_mall_after_sale where create_id=#{createId} and status=0")
    List<MallAfterSaleInfo> selectByCreateId(int createId);
    @Select("select count(id) from mall_mall_after_sale where status=0")
    int selectAllCount();
}

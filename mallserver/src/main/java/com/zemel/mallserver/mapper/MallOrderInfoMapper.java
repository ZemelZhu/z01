package com.zemel.mallserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zemel.data.entiy.ProductOrderInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/3/22 18:25
 */
public interface MallOrderInfoMapper extends BaseMapper<ProductOrderInfo> {
    @Select("select * from mall_product_order where status=0 limit #{index},10")
    List<ProductOrderInfo> selectByPage(int index);
    @Select("select * from mall_product_order where create_id=#{createId} and status=0")
    List<ProductOrderInfo> selectByCreateId(int createId);
    @Select("select count(*) from mall_product_order where status=0")
    int selectAllCount();
}

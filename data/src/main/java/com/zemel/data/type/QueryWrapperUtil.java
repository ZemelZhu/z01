package com.zemel.data.type;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @Author: zemel
 * @Date: 2020/5/1 14:34
 */
public class QueryWrapperUtil {
    public static <T> QueryWrapper<T> buildDataWrapper(Class<T> clazz)
    {
        return buildDataWrapper(DataStatus.NORMAL,clazz);
    }
    public static <T> QueryWrapper<T> buildDataWrapper(DataStatus status,Class<T> clazz)
    {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",status.ordinal());
        return queryWrapper;
    }
}

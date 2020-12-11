package com.zemel.framework.until;

import com.zemel.framework.vo.ResponseVo;

import java.lang.reflect.Field;

/**
 * @Author: zemel
 * @Date: 2020/4/26 22:24
 */
public class ReflectUtil {
    public static <T> T getFiled(Object object, String filedName, Class<T> clazz) {
        try {
            Field field = object.getClass().getDeclaredField(filedName);
            field.setAccessible(true);
            return (T) field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ResponseVo build = ResponseVo.build(true);
        String message = getFiled(build, "a", String.class);
        System.out.println(message);
    }
}

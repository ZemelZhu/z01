package com.zemel.framework.until;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zemel
 * @Date: 2020/7/17 23:03
 */
public class ClassUtil {
    public static boolean isBaseType(Class className) {
//        if(className.equals(String.class))
//            return true;
        return className.equals(Integer.class) ||
                className.equals(Byte.class) ||
                className.equals(Long.class) ||
                className.equals(Double.class) ||
                className.equals(Float.class) ||
                className.equals(Character.class) ||
                className.equals(Short.class) ||
                className.equals(Boolean.class);
    }

    public static boolean isCollection(Object object) {
        if (object instanceof List)
            return true;
        return false;
    }
    public static List<Field> getAllFile(Class<?> clazz)
    {
        ArrayList<Field> fields = new ArrayList<>();
        while(true)
        {
            Field[] declaredFields = clazz.getDeclaredFields();
            fields.addAll(Arrays.asList(declaredFields));
            clazz = clazz.getSuperclass();
            if(clazz==null)
                break;
        }
        return fields;
    }
    public static boolean isInteger(Class<?> type)
    {
        if(type.isAssignableFrom(int.class)||type.isAssignableFrom(Integer.class))
            return true;
        return false;
    }
    public static boolean isLong(Class<?> type)
    {
        if(type.isAssignableFrom(long.class)||type.isAssignableFrom(Long.class))
            return true;
        return false;
    }
    public static boolean isFloat(Class<?> type)
    {
        if(type.isAssignableFrom(float.class)||type.isAssignableFrom(Float.class))
            return true;
        return false;
    }
    public static boolean isDouble(Class<?> type)
    {
        if(type.isAssignableFrom(double.class)||type.isAssignableFrom(Double.class))
            return true;
        return false;
    }public static boolean isBoolean(Class<?> type)
    {
        if(type.isAssignableFrom(boolean.class)||type.isAssignableFrom(Boolean.class))
            return true;
        return false;
    }
}

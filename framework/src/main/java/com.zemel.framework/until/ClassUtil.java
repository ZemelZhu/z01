package com.zemel.framework.until;

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
}

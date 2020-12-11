package com.zemel.framework.until;

/**
 * @Author: zemel
 * @Date: 2020/1/16 18:54
 */
public class FormatUtil {
    /**
     * 字段名转换(驼峰自动映射处理) ps:create_time -> createTime
     */
    public static String toUppercase4FirstLetter(String fieldName) {
        String[] split = fieldName.split("_");
        StringBuilder buffer = new StringBuilder();
        buffer.append(split[0]);
        for (int i = 1; i < split.length; i++) {
            String str = split[i];
            buffer.append(str.substring(0, 1).toUpperCase());
            buffer.append(str.substring(1));
        }
        return buffer.toString();

    }
}

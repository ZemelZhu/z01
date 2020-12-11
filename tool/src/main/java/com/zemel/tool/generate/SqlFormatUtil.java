package com.zemel.tool.generate;

import java.util.HashMap;

import java.util.Map;

/**
 * @Author: zemel
 * @Date: 2020/1/16 18:58
 */
public class SqlFormatUtil {
    private static Map<String, String> typeConversionMaps = new HashMap<String, String>();

    static {
        HashMap<String, String[]> hashMap = new HashMap<String, String[]>();
        hashMap.put("String", getTypeConversionString());
        hashMap.put("boolean", getTypeConversionBoolean());
        hashMap.put("int", getTypeConversionInteger());
        hashMap.put("Date", getTypeConversionDate());
        hashMap.put("BigDecimal", getTypeConversionBigDecimal());
        hashMap.put("long", getTypeConversionLong());
        hashMap.put("byte[]", getTypeConversionBytes());
        hashMap.forEach((key, str) -> {
            for (int i = 0; i < str.length; i++) {
                typeConversionMaps.put(str[i], key);
            }
        });
    }

    private static String[] getTypeConversionBytes() {
        String[] strs = {"blob"};
        return strs;
    }

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

    public static String typeConversion(String type) {
        String conversionType = typeConversionMaps.get(type);
        if (conversionType == null) {
            int index = type.indexOf("(");
            if (index > 0) {
                String substring = type.substring(0, type.indexOf("("));
                conversionType = typeConversionMaps.get(substring);
            }
        }
        return conversionType;
    }

    private static String[] getTypeConversionLong() {
        String[] strs = {"bigint"};
        return strs;
    }

    private static String[] getTypeConversionBigDecimal() {
        String[] strs = {"decimal","decimal(11,2)"};
        return strs;
    }

    private static String[] getTypeConversionDate() {
        String[] strs = {"timestamp", "time", "date","datetime"};
        return strs;
    }

    private static String[] getTypeConversionInteger() {
        String[] strs = {"tinyint(4)", "int(11)", "int"};
        return strs;
    }

    private static String[] getTypeConversionBoolean() {
        String[] strs = {"tinyint(1)"};
        return strs;
    }

    private static String[] getTypeConversionString() {
        String[] strs = {"varchar(255)", "varchar", "char"};
        return strs;
    }


}

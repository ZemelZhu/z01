package com.zemel.mallserver.type;

/**
 * @Author: zemel
 * @Date: 2020/5/1 17:00
 */
public class QQVidelUtil {
    public static String getCode(String url)
    {
        try {
            String[] split = url.split("/");
            String code = split[split.length - 1];
            String substring = code.substring(0, code.length() - 5);
            return substring;
        }
        catch (Exception e)
        {
//            throw new TipException("QQ视频码转换错误");
            return "";
        }

    }
}

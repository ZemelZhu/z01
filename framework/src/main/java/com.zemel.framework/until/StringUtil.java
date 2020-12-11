package com.zemel.framework.until;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static final String PHONE_REG_1 = "^\\+[0-9]+(-)[0-9]{2,11}$";
    private static final String PHONE_REG_2 = "^(1)\\d{10}$";
    private static Pattern p1 = Pattern.compile(PHONE_REG_1);
    private static Pattern p2 = Pattern.compile(PHONE_REG_2);
    private static final String MAIL_REG_1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private static Pattern p3 = Pattern.compile(MAIL_REG_1);

    /**
     * 验证手机号是否合法
     * 1. +区号-手机号,例子: +86-13200000000
     * 2. 中国11位手机号,例子: 132000000000
     *
     * @param phone
     * @return
     */
    public static boolean isValidPhoneNumber(String phone) {
        if (phone == null || "".equals(phone)) {
            return false;
        }
        Matcher m1 = p1.matcher(phone);
        Matcher m2 = p2.matcher(phone);
        return m2.find() || m1.find();
    }

    public static boolean isNullOrEmpty(String src) {
        if (src == null || src.isEmpty() || src.trim().length() <= 0) {
            return true;
        }

        return false;
    }

    public static boolean isNullOrEmpty(String... src) {
        if (src == null || src.length == 0)
            return true;
        for (int i = 0; i < src.length; i++) {
            if (isNullOrEmpty(src[i]))
                return true;
        }
        return false;
    }

    /**
     * 功能：将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    public static String initcap(String str) {

        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) {
            return false;
        }
        Matcher m = p3.matcher(email);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
}

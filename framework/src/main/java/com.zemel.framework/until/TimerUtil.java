package com.zemel.framework.until;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: zemel
 * @Date: 2020/4/6 17:07
 */
public class TimerUtil {
    public static String date(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    public static String date1(Date date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.format(date);
        } catch (Exception e) {
            return null;
        }
    }
}

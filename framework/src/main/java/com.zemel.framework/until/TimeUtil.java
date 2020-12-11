package com.zemel.framework.until;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static String nowDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}

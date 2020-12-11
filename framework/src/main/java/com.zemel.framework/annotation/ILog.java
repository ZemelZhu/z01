package com.zemel.framework.annotation;

/**
 * @Author: zemel
 * @Date: 2020/8/5 20:09
 */
public interface ILog {
    public static final String STAT = "|";

    public void writeLog();

    public String getLog();
}

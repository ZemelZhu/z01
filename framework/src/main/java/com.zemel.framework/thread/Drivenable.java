package com.zemel.framework.thread;

/**
 * @Author: zemel
 * @Date: 2020/2/8 19:40
 */
@FunctionalInterface
public interface Drivenable {
    void execute() throws Exception;
}

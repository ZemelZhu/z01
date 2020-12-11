package com.zemel.note.thread;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zemel
 * @Date: 2020/2/29 14:55
 */
public class MyThread {
    public static void main(String[] args) {
        new ConcurrentHashMap(32);
        Thread thread = new Thread(() -> {
            while(true)
            {

            }
        });
        thread.start();
        while(true)
        {

        }
    }
}

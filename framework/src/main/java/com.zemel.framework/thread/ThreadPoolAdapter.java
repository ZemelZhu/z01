package com.zemel.framework.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zemel
 * @Date: 2020/2/8 23:33
 */
public class ThreadPoolAdapter {

    private ExecutorService commonThreadPool;
    private ExecutorService singleThreadExecutor;

    public ThreadPoolAdapter() {
        int threadCount = Runtime.getRuntime().availableProcessors();
        commonThreadPool = Executors.newFixedThreadPool(threadCount * 2);
        singleThreadExecutor = Executors.newSingleThreadExecutor();
    }

    public ExecutorService getCommonThreadPool() {
        return commonThreadPool;
    }

    public ExecutorService getSingleThreadExecutor() {
        return singleThreadExecutor;
    }
}

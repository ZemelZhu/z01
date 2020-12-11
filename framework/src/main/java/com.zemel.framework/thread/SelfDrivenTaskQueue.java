package com.zemel.framework.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * @Author: zemel
 * @Date: 2020/2/8 15:00
 */
public class SelfDrivenTaskQueue {
    protected static final Logger logger = LoggerFactory.getLogger(SelfDrivenAction.class);
    /**
     * 执行Task的线程池
     */
    private ExecutorService threadPool;
    /**
     * 任务队列。队头元素是正在执行的任务。
     */
    private Queue<Runnable> taskQueue;

    private volatile boolean isRunning = false;

    public SelfDrivenTaskQueue(ExecutorService exeService) {
        this.threadPool = exeService;
        // 使用无锁线程安全队列
        this.taskQueue = new ConcurrentLinkedQueue<>();
    }

    /**
     * 往任务队列中添加任务。
     *
     * @param task
     */
    public void add(Runnable task) {
        taskQueue.offer(task);
        driven();
    }

    public void add(FutureTask task) {
        taskQueue.offer(task);
        driven();
    }

    private void driven() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("driven");
            nextDriven();
        }
    }

    /**
     * 完成一个任务。<br>
     * 任务完成的时候，必须调用本方法来驱动后续的任务，“自驱动”没有你想像中的智能哈~
     */
    public void nextDriven() {
        Runnable peek = taskQueue.poll();
        if (peek != null)
            this.threadPool.submit(peek);
        else
            isRunning = false;
    }
}

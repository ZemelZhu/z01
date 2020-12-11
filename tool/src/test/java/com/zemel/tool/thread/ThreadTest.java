package com.zemel.tool.thread;

import com.zemel.framework.thread.SelfDrivenCallableAction;
import com.zemel.framework.thread.SelfDrivenRunnableAction;
import com.zemel.framework.thread.SelfDrivenTaskQueue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zemel
 * @Date: 2020/2/8 15:35
 */
@SpringBootTest
public class ThreadTest {
    static int count = 0;
    AtomicInteger cout = new AtomicInteger(0);
    ExecutorService userCmdThreadPool = Executors.newFixedThreadPool(4);
    public static void main(String[] args) throws InterruptedException {
        ThreadTest threadTest = new ThreadTest();
        for (int i = 0; i < 10; i++)
            threadTest.test1();
    }
    @Test
    void test1() throws InterruptedException {
        SelfDrivenTaskQueue selfDrivenTaskQueue = new SelfDrivenTaskQueue(userCmdThreadPool);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {

                SelfDrivenRunnableAction selfDrivenRunnableAction = new SelfDrivenRunnableAction(() -> {
                    System.out.println("cout:" + cout.getAndIncrement() + "   " + Thread.currentThread().getId());
                    count++;
                });
                SelfDrivenCallableAction<Integer> integerSelfDrivenCallableAction = new SelfDrivenCallableAction<>(() -> {
                    System.out.println("cout:" + cout.getAndIncrement() + "   " + Thread.currentThread().getId());
                    count++;
                    return count;
                });

                selfDrivenRunnableAction.setActionQueue(selfDrivenTaskQueue);
                integerSelfDrivenCallableAction.setActionQueue(selfDrivenTaskQueue);
                selfDrivenTaskQueue.add(selfDrivenRunnableAction);
                selfDrivenTaskQueue.add(integerSelfDrivenCallableAction);
                Integer key = null;
                try {
                    key = integerSelfDrivenCallableAction.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println(key);
            });
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            thread.start();
        }
//        Thread.join();
    }
}

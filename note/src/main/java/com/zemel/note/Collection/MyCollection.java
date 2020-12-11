package com.zemel.note.Collection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zemel
 * @Date: 2020/2/29 14:58
 */
public class MyCollection {
    public MyCollection()
    {

    }
    public MyCollection(int a)
    {
        this();
    }
    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, SQLException {
//        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(1);
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.lockInterruptibly();
//        Thread t =new Thread();
//        t.start();
//        t.isInterrupted();
//        t.interrupt();
//        Thread.interrupted();
        //new TransferQueue();
        Thread.currentThread().join();
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/数据库名称","用户名称","密码");

        Thread.currentThread().join();
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        for(int i =0;i<100;i++)
        {
            map.put(i, UUID.randomUUID().toString());
        }
        new Thread(()->{
            for(int i =101;i<200;i++)
            {
                map.put(i, UUID.randomUUID().toString());
                System.out.println("add");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            Iterator<Map.Entry<Integer, String>> iterator =map.entrySet().iterator();
            int count = 0;
            while (iterator.hasNext())
            {
                count++;
                Map.Entry<Integer, String> next = iterator.next();
                System.out.println(next.getValue()+count);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//        Integer peek = blockingQueue.take();
//        System.out.println(peek);
    }
}

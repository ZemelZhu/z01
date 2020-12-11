package com.zemel.framework.thread;

/**
 * @Author: zemel
 * @Date: 2020/2/8 14:46
 */
public interface ISequenceTask {
    void addCommandTask(SelfDrivenCallableAction task);

    void addCommandTask(SelfDrivenRunnableAction task);
}

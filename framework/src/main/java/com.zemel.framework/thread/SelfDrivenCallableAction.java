package com.zemel.framework.thread;

import com.zemel.framework.until.StackMessagePrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Author: zemel
 * @Date: 2020/2/8 15:13
 */
public class SelfDrivenCallableAction<V> extends FutureTask<V> {
    protected static final Logger logger = LoggerFactory.getLogger(SelfDrivenCallableAction.class);
    private SelfDrivenTaskQueue actionQueue;

    public SelfDrivenCallableAction(Callable<V> callable) {
        super(callable);
    }

    public void setActionQueue(SelfDrivenTaskQueue actionQueue) {
        this.actionQueue = actionQueue;
    }

    @Override
    public void run() {
        try {
            super.run();
        } catch (Exception e) {
            String errorMsg = String.format("action error:%n%s%n",
                    StackMessagePrint.printErrorTrace(e));
            logger.error(errorMsg, e);
        } finally {
            // 不论是否成功执行，均移除此action
            actionQueue.nextDriven();
        }
    }
}

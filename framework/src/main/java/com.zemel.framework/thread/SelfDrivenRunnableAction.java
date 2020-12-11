package com.zemel.framework.thread;

import com.zemel.framework.until.StackMessagePrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: zemel
 * @Date: 2020/2/8 15:15
 */
public class SelfDrivenRunnableAction implements Runnable {
    protected static final Logger logger = LoggerFactory.getLogger(SelfDrivenRunnableAction.class);
    private Drivenable drivenable;
    private SelfDrivenTaskQueue actionQueue;

    public SelfDrivenRunnableAction(Drivenable drivenable) {
        this.drivenable = drivenable;
    }

    public void setActionQueue(SelfDrivenTaskQueue actionQueue) {
        this.actionQueue = actionQueue;
    }

    @Override
    public void run() {
        try {
            drivenable.execute();
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

package com.zemel.framework.bean;

import com.zemel.data.entiy.PlayerInfo;
import com.zemel.framework.ComponentManager;
import com.zemel.framework.bean.room.BaseRoom;
import com.zemel.framework.component.IPlayerComponent;
import com.zemel.framework.thread.ISequenceTask;
import com.zemel.framework.thread.SelfDrivenCallableAction;
import com.zemel.framework.thread.SelfDrivenRunnableAction;
import com.zemel.framework.thread.SelfDrivenTaskQueue;

/**
 * @Author: zemel
 * @Date: 2020/2/10 11:00
 */
public abstract class CorePlayer implements ICorePlayer, ISequenceTask {
    private SelfDrivenTaskQueue cmdQueue;
    private PlayerInfo playerInfo;
    protected boolean isChange;
    protected BaseRoom room;

    public CorePlayer(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
        this.cmdQueue = new SelfDrivenTaskQueue(ComponentManager.getInstance().getCommonThreadPool());
    }

    public boolean updatePlayerInfos() {
        if (isChange)
            ComponentManager.getInstance().getComponent(IPlayerComponent.class).updatePlayerInfo(playerInfo);
        return true;
    }

    public boolean login() {
        return true;
    }

    protected void offline() {

    }

    @Override
    public int getPlayerID() {
        return playerInfo.getId();
    }

    @Override
    public void addCommandTask(SelfDrivenCallableAction task) {
        task.setActionQueue(cmdQueue);
        cmdQueue.add(task);
    }

    @Override
    public void addCommandTask(SelfDrivenRunnableAction task) {
        task.setActionQueue(cmdQueue);
        cmdQueue.add(task);
    }

    public BaseRoom getRoom() {
        return room;
    }

    public void setRoom(BaseRoom room) {
        this.room = room;
    }
}

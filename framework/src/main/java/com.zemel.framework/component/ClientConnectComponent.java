package com.zemel.framework.component;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.socket.netty.IClientConnection;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: zemel
 * @Date: 2020/2/14 14:40
 */
public abstract class ClientConnectComponent implements IComponent {
    private static final int PING_MAX_VALUE = 5;
    @Autowired
    private ITimerComponent timerComponent;
    protected Set<IClientConnection> clientConnections;

    public void addClientConnection(IClientConnection conn) {
        clientConnections.add(conn);
    }

    public void removeClientConnection(IClientConnection conn) {
        clientConnections.remove(conn);
    }

    @Override
    public boolean initialize() {
        clientConnections = Collections.synchronizedSet(new HashSet<>());
        return true;
    }

    protected abstract Object getPingPackage();

    protected int getPingMaxValue() {
        return PING_MAX_VALUE;
    }

    protected void pingAll() {
        //LOGGER.error("allPing");
        Iterator<IClientConnection> iterator = clientConnections.iterator();
        while (iterator.hasNext()) {
            IClientConnection connection = iterator.next();
            if (connection.getPing() >= getPingMaxValue()) {
                iterator.remove();
                connection.onDisconnect();
            }
            connection.send(getPingPackage());
            connection.setPing(connection.getPing() + 1);

        }
    }

    @Override
    public boolean start() {
        timerComponent.addDefaultDelayJob(new Job() {
            @Override
            public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
                if (!ComponentManager.getInstance().isStoping())
                    pingAll();
            }
        }.getClass(), 60 * 1000);
        return true;
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean reload() {
        return false;
    }
}

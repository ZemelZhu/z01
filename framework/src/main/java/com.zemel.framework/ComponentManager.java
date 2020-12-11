package com.zemel.framework;

import com.zemel.data.type.ServerStatus;
import com.zemel.framework.component.IComponent;
import com.zemel.framework.config.ServerConfig;
import com.zemel.framework.sdk.WeChatNotifySdk;
import com.zemel.framework.thread.ThreadPoolAdapter;
import com.zemel.framework.until.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class ComponentManager {
    private static final Logger ConsoleLogger = LoggerFactory.getLogger(LogUtils.logg1);
    @Autowired
    private ServerConfig serverConfig;
    @Autowired(required = false)
    private List<IComponent> components;
    @Autowired
    private WeChatNotifySdk weChatNotifySdk;
    private volatile int serverStatus;
    private ThreadPoolAdapter threadPoolAdapter;
    private Map<Class<?>, IComponent> componentMaps;
    private IDUtil idUtil;

    private void init() {
        idUtil = new IDUtil(serverConfig.getWorkerId(), serverConfig.getDecenterId(), 1);
    }

    public boolean isStoping() {
        return serverStatus == ServerStatus.Stop.ordinal();
    }

    public static ComponentManager getInstance() {
//        return SpringUtils.getBean(ComponentManager.class);
        return instace;
    }

    public boolean isDev() {
        return serverConfig.isDev();
    }

    public long getUnionId() {
        return idUtil.nextId();
    }

    public <T extends IComponent> T getComponent(Class<T> className) {
        if (componentMaps.containsKey(className))
            return (T) componentMaps.get(className);
        T bean = SpringUtils.getBean(className);
        componentMaps.put(className, bean);
        return bean;
    }

    private static ComponentManager instace;

    public ComponentManager() {
        threadPoolAdapter = new ThreadPoolAdapter();
        componentMaps = new HashMap<>();
    }

    /**
     * 系统逻辑起点
     */
    @PostConstruct
    public void start() {
        try {
            init();
            instace = this;
            ConsoleLogger.info("启动逻辑组件开始");
            if (components != null) {
                for (IComponent component : components) {
                    ConsoleLogger.info(component.getClass().getName() + " 组件启动");
                    if (component.initialize()) {
                        if (component.start()) {

                            ConsoleLogger.info(component.getClass().getSimpleName() + " 组件启动成功");
                            continue;
                        }
                    }
                    ConsoleLogger.info(component.getClass().getSimpleName() + " 组件启动失败");
                    exit();
                }
            }
            ConsoleLogger.info("启动逻辑组件正常结束");
            serverMessage("服务器启动");
        } catch (Exception e) {
            ConsoleLogger.info(StackMessagePrint.printErrorTrace(e));
            exit();
        } finally {

        }
    }

    @PreDestroy
    public void stop() {
        serverMessage("服务器关闭");
        ConsoleLogger.info("销毁逻辑组件开始");
        if (components != null) {
            for (IComponent component : components) {
                component.stop();
                ConsoleLogger.info(component.getClass().getSimpleName() + " 组件销毁成功");
            }
        }
        ConsoleLogger.info("销毁逻辑组件结束");
    }

    public void serverMessage(String message) {
        if (!serverConfig.isDev()) {
            StringBuilder builder = new StringBuilder();
            String time = TimeUtil.nowDateString();
            builder.append("时间").append(time).append("\n");
            builder.append("项目名:").append(serverConfig.getName()).append("\n")
                    .append("服务器id:").append(serverConfig.getDecenterId()).append("\n")
                    .append("消息:").append(message);
            weChatNotifySdk.notify(builder.toString());
        }
    }

    public void exit() {
        ConsoleLogger.info("start fail");
//        System.exit(0);
    }

    public ExecutorService getCommonThreadPool() {
        return threadPoolAdapter.getCommonThreadPool();
    }

    public ExecutorService getSingleThreadExecutor() {
        return threadPoolAdapter.getSingleThreadExecutor();
    }
}

package com.zemel.framework.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface IComponent {
    static final Logger LOGGER = LoggerFactory.getLogger(IComponent.class);

    /**
     * 初始化组件
     *
     * @return
     */
    boolean initialize();

    /**
     * 启动组件
     *
     * @return
     */
    boolean start();

    /**
     * 停止组件
     */
    void stop();

    /**
     * 重新加载组件
     */
    boolean reload();
}

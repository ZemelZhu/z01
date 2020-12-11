package com.zemel.webserver.component;

import com.zemel.framework.component.IZookeeperComponent;

/**
 * @Author: zemel
 * @Date: 2020/3/3 23:01
 */
//@Component
public class ZookeeperComponent extends IZookeeperComponent {
    public void testLock(int id)
    {
        acquireDistributedLock((long) id);
    }
}

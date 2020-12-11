package com.zemel.framework.hotswap;

import com.zemel.framework.component.IComponent;
import com.zemel.framework.serialize.ClassInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Set;

/**
 * @Author: zemel
 * @Date: 2020/3/4 17:11
 */
public interface HotSwap {
    static final Logger LOGGER = LoggerFactory.getLogger(IComponent.class);

    boolean doAgentReload(File agent);

    File buildJar(Set<ClassInfo> classInfos);
}

package com.zemel.tool.hotswap;

import com.zemel.framework.hotswap.AgentHotSwap;
import com.zemel.framework.hotswap.HotSwap;
import com.zemel.framework.serialize.ClassInfo;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Set;

/**
 * @Author: zemel
 * @Date: 2020/3/4 18:59
 */
@Service
public class HotSwapTool {
    private HotSwap hotSwap;
    public HotSwapTool()
    {
        this.hotSwap = new AgentHotSwap();
    }
    public String doHotSwap(Set<ClassInfo> classInfos)
    {
        File file = hotSwap.buildJar(classInfos);
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }

    public boolean doHotSwap(File file) {
        return hotSwap.doAgentReload(file);
    }
}

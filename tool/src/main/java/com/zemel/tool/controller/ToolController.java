package com.zemel.tool.controller;

import com.zemel.framework.serialize.ClassInfo;
import com.zemel.tool.hotswap.HotSwapTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.HashSet;

/**
 * @Author: zemel
 * @Date: 2020/3/4 19:11
 */
@RestController
@RequestMapping("tool")
public class ToolController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToolController.class);
    @Autowired
    private HotSwapTool hotSwapTool;
    public void hotSwap()
    {
        String file = "";
        HashSet<String> files = new HashSet<>();
        files.add(file);
    }
    @RequestMapping("testHotSwap")
    public String testHotSwap()
    {
        String ttt = "sd是11的323z11323";
        HashSet<ClassInfo> classInfos = new HashSet<>();
        classInfos.add(ClassInfo.build(getClass()));
        String s = hotSwapTool.doHotSwap(classInfos);
        return s;
    }
    @RequestMapping("testHotSwap1")
    public String testHotSwap1()
    {
        String ttt = "哈士奇1111现场v54656";
        return ttt;
    }
    @RequestMapping("testHotSwap2")
    public String testHotSwap2(String file)
    {
        File file1 = new File(file);
        if(file1!=null&&file.isEmpty()==false)
        {
            LOGGER.error(file1.getAbsolutePath());
             hotSwapTool.doHotSwap(file1);
        }
        return file;
    }
}

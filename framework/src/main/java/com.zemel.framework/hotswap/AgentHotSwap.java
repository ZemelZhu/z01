package com.zemel.framework.hotswap;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.zemel.framework.serialize.ClassInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.management.ManagementFactory;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.stream.Collectors;

/**
 * @Author: zemel
 * @Date: 2020/3/4 17:14
 */
public class AgentHotSwap implements HotSwap {
    @Override
    public boolean doAgentReload(File agent) {
        LOGGER.error(agent.getAbsolutePath() + "   class:" + this.getClass().getName());
        // 替换agent.jar
        VirtualMachineDescriptor gameVMDescriptor = null;
        String serverPid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        for (VirtualMachineDescriptor vmDescriptor : VirtualMachine.list()) {
            String pid = vmDescriptor.id();
            if (serverPid.equals(pid)) {
                gameVMDescriptor = vmDescriptor;
                break;
            }
        }
        try {
            if (gameVMDescriptor != null) {
                if (agent.exists()) {
                    LOGGER.error(agent.getAbsolutePath() + "   class:" + this.getClass().getName());
                    VirtualMachine virtualMachine = VirtualMachine.attach(gameVMDescriptor);

                    virtualMachine.loadAgent(agent.getAbsolutePath(), agent.getAbsolutePath());
                    virtualMachine.detach();
                } else {
                    throw new IllegalStateException(String.format("AgentFile[%s]不存在！", agent.getCanonicalPath()));
                }
            } else {
                throw new IllegalStateException(String.format("没有找到匹配进程!"));
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
        LOGGER.debug(String.format("Agent Reload execute success!"));
        return true;
    }

    @Override
    public File buildJar(Set<ClassInfo> classInfos) {
        try {
            String fileParam = String.join(",", classInfos.stream().map(f -> f.getNameFile()).collect(Collectors.toList()));
            classInfos.add(ClassInfo.build(GameAgentMain.class));
            classInfos.add(ClassInfo.build(ClassInfo.class));
            File agent = new File("agent.jar");
            agent.delete();
            JarOutputStream target = new JarOutputStream(new FileOutputStream(agent), getManifest(fileParam));
            for (ClassInfo classInfo : classInfos) {
                JarEntry entry = new JarEntry(classInfo.getNameFile());
                target.putNextEntry(entry);
                target.write(classInfo.getBytes());
                target.closeEntry();
            }
            target.close();
            LOGGER.error("pack jar file success!");
            return agent;
        } catch (Exception e) {
            LOGGER.error("PackAgenJar error:" + e.toString());
            e.printStackTrace();
            return null;
        } finally {

        }
    }

    private Manifest getManifest(String fileParam) {
        // 准备manifest.mf文件
        Manifest manifest = new Manifest();
        Attributes mainAttributes = manifest.getMainAttributes();
        mainAttributes.putValue("Manifest-Version", "1.0");
        mainAttributes.putValue("Can-Retransform-Classes", "true");
        mainAttributes.putValue("Agent-Class", GameAgentMain.class.getName());
        mainAttributes.putValue("File-Params", fileParam);
        mainAttributes.putValue("Can-Redefine-Classes", "true");
        return manifest;
    }


}

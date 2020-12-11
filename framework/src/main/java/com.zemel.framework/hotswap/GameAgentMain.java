package com.zemel.framework.hotswap;

import com.zemel.framework.serialize.ClassInfo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * @Author: zemel
 * @Date: 2020/3/4 18:28
 */
public class GameAgentMain {
    //private static final Logger LOGGER = LoggerFactory.getLogger(GameAgentMain.class);
    private static Map<String, ClassInfo> classInfos = new HashMap<>();
    ;

    public static void agentmain(String args, Instrumentation inst) {
        System.out.println("2222");
        System.out.println("===reload Agent begin===" + "   args: " + args);
        try {
            // inst.addTransformer(new GameClassFileTransformer(), true);
            getClassInfo(args);
            for (Class<?> clazz : inst.getAllLoadedClasses()) {
                ClassInfo classInfo = classInfos.get(clazz.getName());
                if (clazz.getName().contains("ToolController") || clazz.getName().contains("GameAgentMain")) {
                    System.out.println("  " + clazz);
                }
                if (classInfo != null) {
                    classInfo.setClazz(clazz);
                }

            }
            Class<?>[] classes = classInfos.values().stream().filter(f -> f.getClazz() != null).map(f -> f.getClazz()).toArray(Class<?>[]::new);

            if (classes.length > 0) {
                //inst.retransformClasses(classes);
                for (ClassInfo classInfo : classInfos.values()) {
                    if (classInfo.getClazz() != null) {
                        ClassDefinition classDefinition = new ClassDefinition(classInfo.getClazz(), classInfo.getBytes());
                        inst.redefineClasses(classDefinition);
                    }
                }
                System.out.println(Arrays.toString(classes) + "  hotswap success");

            } else {
                System.out.println("没有要修改的类，可能是没加载到虚拟机里面，或者是agent.jar的File-Params设置有问题!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("===reload Agent complate===");
    }

    private static void getClassInfo(String filePath) throws Exception {
        classInfos = new HashMap<>();
        File agent = new File(filePath);
        JarFile agentJar = new JarFile(agent);
        Manifest manifest = agentJar.getManifest();
        Attributes attributes = manifest.getMainAttributes();
        String fileParams = attributes.getValue("File-Params");
        for (String jarEntryName : fileParams.split(",")) {
            JarEntry jarEntry = agentJar.getJarEntry(jarEntryName);
            if (jarEntry != null) {
                InputStream is = null;
                is = agentJar.getInputStream(jarEntry);
                byte[] bytes = new byte[1024];
                int len = -1;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((len = is.read(bytes)) != -1) {
                    baos.write(bytes, 0, len);
                }
                bytes = baos.toByteArray();
                baos.close();
                ClassInfo classInfo = ClassInfo.build(bytes, jarEntryName);
                classInfos.put(classInfo.getClassName(), classInfo);
            }
        }
        agentJar.close();
    }

    private static class GameClassFileTransformer implements ClassFileTransformer {
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
            ClassInfo classInfo = classInfos.get(className);
            if (classInfo != null)
                return classInfo.getBytes();
            return null;
        }
    }
}

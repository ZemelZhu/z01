package com.zemel.framework.serialize;

import com.zemel.framework.until.FileUtil;
import lombok.Data;

import java.io.File;

/**
 * @Author: zemel
 * @Date: 2020/3/4 22:39
 */
@Data
public class ClassInfo {
    private String name;
    private String simpleName;
    private Class<?> clazz;
    private byte[] bytes;
    private File file;

    public static ClassInfo build(Class<?> clazz) {
        ClassInfo classInfo = new ClassInfo();
        classInfo.setClazz(clazz);
        classInfo.setSimpleName(clazz.getSimpleName() + ".class");
        classInfo.setName(clazz.getName() + ".class");
        String path = clazz.getResource("").getPath() + classInfo.getSimpleName();
        File file = new File(path);
        classInfo.setFile(file);
        classInfo.setBytes(FileUtil.getBytesByFile(file));
        return classInfo;
    }

    public static ClassInfo build(byte[] bytes, String name) {
        ClassInfo classInfo = new ClassInfo();
        classInfo.setBytes(bytes);
        classInfo.setName(name.replace("/", "."));
        return classInfo;
    }

    public String getClassName() {
        if (clazz != null)
            return clazz.getName();
        return name.replace(".class", "");
    }

    public String getNameFile() {
        String className = clazz.getName();
        String replace = className.replace(".", "/");
        return replace + ".class";

    }
}

package com.zemel.framework.until;

import java.io.*;

public class FileUtil {
    public static boolean writeFile(String content, String path) {
        PrintWriter pw = null;
        try {
            FileWriter fw = new FileWriter(path);
            pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            pw.close();
        }
    }

    public static byte[] getBytesByFile(File file) {
        try {
            //获取输入流
            FileInputStream fis = new FileInputStream(file);

            //新的 byte 数组输出流，缓冲区容量1024byte
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            //缓存
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            //改变为byte[]
            byte[] data = bos.toByteArray();
            //
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
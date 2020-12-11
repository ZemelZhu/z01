package com.zemel.framework.until;

import com.zemel.framework.serialize.Protostuff;

/**
 * @Author: zemel
 * @Date: 2020/7/18 14:08
 */
public class SerializeUtil {
    private static Protostuff protostuff = new Protostuff();

    public static <T> byte[] serialize(T source) {
        return protostuff.serialize(source);
    }

    public static <T> T deserialize(byte[] source, Class<T> clazz) {
        return protostuff.deserialize(source, clazz);
    }
}

package com.zemel.framework.serialize;

/**
 * @Author: zemel
 * @Date: 2020/2/14 13:39
 */
public interface Serialize {
    public <T> byte[] serialize(T source);

    public <T> T deserialize(byte[] source, Class<T> typeClass);
}

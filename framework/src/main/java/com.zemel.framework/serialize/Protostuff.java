package com.zemel.framework.serialize;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zemel
 * @Date: 2020/2/14 13:42
 */
public class Protostuff implements Serialize {
    /**
     * 避免每次序列化都重新申请Buffer空间
     */
    private LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
    /**
     * 缓存Schema
     */
    private Map<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap<>();

    @Override
    public <T> byte[] serialize(T source) {
        Class<T> clazz = (Class<T>) source.getClass();
        Schema<T> schema = getSchema(clazz);
        byte[] data;
        try {
            data = ProtostuffIOUtil.toByteArray(source, schema, buffer);
        } finally {
            buffer.clear();
        }

        return data;

    }

    @Override
    public <T> T deserialize(byte[] source, Class<T> clazz) {
        T obj = null;
        try {
            Schema<T> schema = getSchema(clazz);
            obj = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(source, obj, schema);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;

    }

    private <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) schemaCache.get(clazz);
        if (Objects.isNull(schema)) {
            //这个schema通过RuntimeSchema进行懒创建并缓存
            //所以可以一直调用RuntimeSchema.getSchema(),这个方法是线程安全的
            schema = RuntimeSchema.getSchema(clazz);
            if (Objects.nonNull(schema)) {
                schemaCache.put(clazz, schema);
            }
        }

        return schema;
    }

}

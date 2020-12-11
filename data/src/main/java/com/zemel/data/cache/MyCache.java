package com.zemel.data.cache;

import org.apache.ibatis.cache.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zemel
 * @Date: 2020/5/4 18:40
 */
public class MyCache implements Cache {
    private Map<Object,Object> caches = new ConcurrentHashMap<>();
    private String id;
    public MyCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object o, Object o1) {
        caches.put(o,o1);
    }

    @Override
    public Object getObject(Object o) {
        return caches.get(o);
    }

    @Override
    public Object removeObject(Object o) {
        return caches.remove(o);
    }

    @Override
    public void clear() {
        caches.clear();
    }

    @Override
    public int getSize() {
        return caches.size();
    }
}

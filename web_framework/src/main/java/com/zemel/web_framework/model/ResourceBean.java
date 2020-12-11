package com.zemel.web_framework.model;

import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/7/19 22:03
 */
@Data
public class ResourceBean {
    private String resource;
    private long createTime;
    private String owner;

    public ResourceBean(String resource,  String owner) {
        this.resource = resource;
        this.owner = owner;
        this.createTime = System.currentTimeMillis();
    }
}

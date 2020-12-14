package com.zemel.framework.annotation;

/**
 * @Author: zemel
 * @Date: 2020/12/12 17:43
 */
/**
 * 服务器内部消息
 * */
public interface InnerMessage extends IMessage {
    public void handle();
}

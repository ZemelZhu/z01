package com.zemel.framework.bean;

import lombok.Data;

/**
 * @Author: zemel
 * @Date: 2020/2/9 13:04
 */
@Data
public class ConversionMessage {
    private int classId;
    private int methodId;
    private byte[] messageBody;
    private Object message;

    public ConversionMessage(short classId, byte[] messageBody) {
        this.classId = classId;
        this.messageBody = messageBody;
    }
}

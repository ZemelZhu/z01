package com.zemel.framework.exception;

/**
 * @Author: zemel
 * @Date: 2020/5/1 23:33
 */
public class TipException extends RuntimeException {
    private boolean record;

    public TipException(String message) {
        super(message);
    }

    public TipException(String message, boolean record) {
        super(message);
        this.record = record;
    }

    public boolean isRecord() {
        return record;
    }

    public void setRecord(boolean record) {
        this.record = record;
    }
}

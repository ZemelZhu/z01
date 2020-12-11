package com.zemel.data.type;

/**
 * @Author: zemel
 * @Date: 2020/5/5 14:28
 */
public enum DataStatus {
    NORMAL("正常"),
    DELETE("删除"),
    LOCK("锁定"),
    INVALID("失效"),
            ;
    private String value;

    DataStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static DataStatus parse(int type)
    {
        for(DataStatus key:DataStatus.values())
        {
            if(key.ordinal()==type)
                return key;
        }
        return null;
    }
}

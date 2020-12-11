package com.zemel.mallserver.type;

/**
 * @Author: zemel
 * @Date: 2020/5/2 21:29
 */
public enum AfterSaleType {
    NONE("空"),
    SERVICE("维修"),   //维修
    MAINTAIN("保养"),  //保养
    ;
    private String value;
    public static AfterSaleType parse(int type)
    {
        for(AfterSaleType key:AfterSaleType.values())
        {
            if(key.ordinal()==type)
                return key;
        }
        return null;
    }
    AfterSaleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

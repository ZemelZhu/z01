package com.zemel.web_framework.type;

/**
 * @Author: zemel
 * @Date: 2020/7/18 11:22
 */
public enum  UserType {
    USER("普通用户"),
    PLAYER("普通玩家"),
    ADMIN("后台管理"),
    SYSTEM("系统"),
    ;
    private String value;

    UserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static UserType parse(int type)
    {
        for(UserType key:UserType.values())
        {
            if(key.ordinal()==type)
                return key;
        }
        return null;
    }
}

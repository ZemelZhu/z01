package com.zemel.data.type;

/**
 * @Author: zemel
 * @Date: 2020/5/4 13:57
 */
public enum  Roles {
    USER("SyyY&Tis9w!PVCJI"),   // 账号用户
    PLAYER("ZdLYutE&QXj@YV7X"), // 游戏玩家
    ADMIN("vuoX5ukq$@OvUlIt"),  // 管理员
    ; // 玩家
    private String secret;
    Roles(String secret) {
        this.secret =secret;
    }

    public static Roles parse(Integer type) {
        for(Roles roles:Roles.values())
        {
            if(roles.ordinal()==type)
                return roles;
        }
        return null;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}

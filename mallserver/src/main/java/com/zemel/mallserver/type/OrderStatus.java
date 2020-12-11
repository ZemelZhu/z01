package com.zemel.mallserver.type;

/**
 * @Author: zemel
 * @Date: 2020/4/6 15:36
 */
public enum OrderStatus {
    CREATE("未处理"),
    AGREE("受理"),
    REFUSE("不受理"),
    ;
    private String name;

    public static String getName(int key) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.ordinal() == key)
                return orderStatus.name;
        }
        return null;
    }

    OrderStatus(String name) {
        this.name = name;
    }
}

package com.zemel.data.protocol;

/**
 * @Author: zemel
 * @Date: 2020/2/9 18:51
 */
public interface Protocol {
    public static final int LOGIN = 1;
    public static final int NOTIFY_WEBSERVER = 2;
    public static final int ROOM = 3;
    public static final int PLAYER_MSG = 4;
    public static final int SCENE = 5;
    public static final int PING = 6;
}

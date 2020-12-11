package com.zemel.framework.socket.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface IMessageHandler {
    static final Logger LOGGER = LoggerFactory.getLogger(IMessageHandler.class);

    void process(IClientConnection conn, Object packet);
}

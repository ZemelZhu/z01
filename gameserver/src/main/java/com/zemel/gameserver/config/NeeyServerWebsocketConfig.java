package com.zemel.gameserver.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class NeeyServerWebsocketConfig {
	@Value("${netty.port}")
	private int port;
	@Value("${netty.address}")
	private String address;
	@Value("${netty.host}")
	private String host;

}

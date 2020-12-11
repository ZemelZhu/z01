package com.zemel.framework.socket.websocket;


//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//
//import com.zemel.framework.until.CodeUntil;
//
//import javax.websocket.*;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.nio.ByteBuffer;

//@Controller
//@ServerEndpoint(value = "/gameServer")
//public class GameSocketServer {
//
//	private static final Logger log = LoggerFactory.getLogger(GameSocketServer.class);
//
//
//
//	@OnOpen
//	public void onOpen(Session session) {
//			log.error("websocket open"+session.getId());
//	}
//
//	@OnClose
//	public void onClose(Session session) {
//
//		log.error("websocket close"+session.getId());
//
//	}
//
//	@OnError
//	public void onError(Session session, Throwable error) {
//		log.error("websocket error"+session.getId());
//	}
//
//	/**
//	 * 收到客户端消息后调用的方法
//	 *
//	 * @param msg 客户端发送过来的消息
//	 */
//	@OnMessage
//	public void onMessage(String msg, Session session) {
//
//		log.error("websocket message"+session.getId()+" message:  "+msg);
//	}
//	@OnMessage
//	public void onMessage(byte[] messages, Session session) throws IOException {
//
//		log.error("websocket message"+session.getId()+" message:  "+messages);
//		byte[] bytes = CodeUntil.deCode(messages);
//		if(bytes!=null)
//		{
//			ByteBuffer wrap = ByteBuffer.wrap(bytes);
//			session.getBasicRemote().sendBinary(wrap);
//		}
//	}


//}

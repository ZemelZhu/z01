package com.zemel.gameserver.logic.component;

import com.zemel.data.proto.entiy.LoginMsg;
import com.zemel.framework.component.ServerComponent;
import com.zemel.framework.socket.netty.IClientConnection;
import com.zemel.framework.until.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameServerComponent extends ServerComponent {
//    @Autowired
//    private NeeyServerWebsocketConfig neeyServerWebsocketConfig;
    @Autowired
    private PlayerComponent playerComponent;
    @Override
public void loginClient(IClientConnection conn, byte[] message) {
    try {

        LoginMsg.LoginMsgCS LoginMsgCS = LoginMsg.LoginMsgCS.parseFrom(message);
        int userID = LoginMsgCS.getUserID();
        String password = LoginMsgCS.getPassword();
        if(userID>0&& StringUtil.isNullOrEmpty(password)==false)
        {
            playerComponent.gamePlayerLogin(userID,password,conn);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}

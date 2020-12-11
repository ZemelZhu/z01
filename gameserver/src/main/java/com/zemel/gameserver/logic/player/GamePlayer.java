package com.zemel.gameserver.logic.player;

import com.google.protobuf.ByteString;
import com.zemel.data.entiy.PlayerInfo;
import com.zemel.data.proto.entiy.CommonMsg;
import com.zemel.framework.bean.CorePlayer;
import com.zemel.framework.socket.netty.IClientConnection;
import com.zemel.framework.socket.netty.IConnectionHolder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

public class GamePlayer extends CorePlayer implements IConnectionHolder {
    private IClientConnection clientConn;

    public GamePlayer(IClientConnection clientConn, PlayerInfo playerInfo) {
        super(playerInfo);
        this.clientConn = clientConn;
    }

    @Override
    public void send(CommonMsg.CommonMsgPB message) {
        ByteBuf buf = Unpooled.wrappedBuffer(message.toByteArray());
        send(new BinaryWebSocketFrame(buf));
    }

    public void reconnection(IClientConnection client) {
        if (clientConn != null)
        {
            clientConn.closeConnection(true);
            clientConn.setServerClosed(true);
        }
        this.clientConn = client;
    }

    @Override
    public void onDisconnect() {

        clientConn.closeConnection(true);
    }

    @Override
    public IClientConnection getClientConnection() {
        return clientConn;
    }

    @Override
    public void setClientConnection(IClientConnection conn) {
        this.clientConn = conn;
    }

    @Override
    public void send(Object message) {
        if (clientConn != null)
            clientConn.send(message);
    }

    @Override
    public void send(ByteString message) {

    }
}

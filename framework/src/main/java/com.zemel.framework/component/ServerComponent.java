package com.zemel.framework.component;

import com.zemel.data.entiy.ServerClusterInfo;
import com.zemel.data.mapping.ServerClusterMapping;
import com.zemel.data.proto.entiy.LoginMsg;
import com.zemel.data.type.ServerStatus;
import com.zemel.data.type.ServerType;
import com.zemel.framework.config.ServerConfig;
import com.zemel.framework.socket.net.ServerClient;
import com.zemel.framework.socket.netty.IClientConnection;
import com.zemel.framework.socket.netty.ServerAdapter;
import com.zemel.framework.socket.netty.server.NettyAdapter;
import com.zemel.framework.socket.netty.websocket.NettyWebsocketAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerComponent implements IComponent {

    @Autowired
    protected ServerConfig serverConfig;
    @Resource
    protected ServerClusterMapping serverClusterMapping;

    protected ServerAdapter serverAdapter;

    protected Map<Integer, ServerClient> servers;

    public ServerClient getServerById(int serverId) {
        return servers.get(serverId);
    }

    public Collection<ServerClient> getAllServer() {
        return servers.values();
    }

    protected void init(ServerAdapter ServerAdapter) {
        this.serverAdapter = ServerAdapter;
    }

    @Override
    public boolean initialize() {
        servers = new ConcurrentHashMap<>();
        if (serverConfig != null) {
            ServerClusterInfo serverClusterInfo = serverClusterMapping.selectById(serverConfig.getDecenterId());
            if (serverClusterInfo != null && serverClusterInfo.getPort() > 0 && serverClusterInfo.getServerState() == ServerStatus.Running.ordinal()) {
                serverClusterInfo.getServerType();
                if (serverClusterInfo.getServerType() == ServerType.GameServer.ordinal())
                    init(new NettyWebsocketAdapter(serverClusterInfo.getPort(),
                            serverClusterInfo.getAddress()));
                else
                    init(new NettyAdapter(serverClusterInfo.getPort()));
                return true;
            }
            LOGGER.info("启动ServerComponent fail serverConfig" + serverConfig);
        }
        return false;
    }

    @Override
    public boolean start() {
        return serverAdapter.start();
    }

    @Override
    public void stop() {
        serverAdapter.stop();
    }

    @Override
    public boolean reload() {
        serverAdapter.stop();
        return serverAdapter.start();
    }

    public void loginClient(IClientConnection conn, byte[] message) {
        try {
            LoginMsg.ServerLoginMsg serverLoginMsg = LoginMsg.ServerLoginMsg.parseFrom(message);
            int serverId = serverLoginMsg.getServerId();
            if (serverId > 0) {
                ServerClient server = new ServerClient();
                server.setServerID(serverId);
                server.setClientConnection(conn);
                servers.put(serverId, server);
                conn.setHolder(server);
                LOGGER.error("serverID:" + serverId + "   loginSuccess");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeServerClient(ServerClient serverClient) {
        servers.remove(serverClient.getServerID());
    }
}

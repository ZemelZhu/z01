package com.zemel.framework.component;

import com.zemel.data.entiy.ServerClusterInfo;
import com.zemel.data.mapping.ServerClusterMapping;
import com.zemel.data.proto.entiy.LoginMsg;
import com.zemel.data.protocol.Protocol;
import com.zemel.data.type.ServerType;
import com.zemel.framework.config.ServerConfig;
import com.zemel.framework.factory.NettyCommonCodecFactory;
import com.zemel.framework.socket.net.CommonMessage;
import com.zemel.framework.socket.netty.handler.ServerMessageHandler;
import com.zemel.framework.socket.netty.server.NettyServerConnector;
import com.zemel.framework.socket.netty.server.ServerConnector;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zemel
 * @Date: 2020/1/19 23:23
 */
public abstract class AbstractBridgeClientsComponent implements IComponent {
    @Autowired
    private ServerConfig serverConfig;

    @Resource
    private ServerClusterMapping serverClusterMapping;

    private Map<Integer, ServerConnector> servers;

    public Collection<ServerConnector> getServers() {
        return servers.values();
    }

    public ServerConnector getServerById(int serverId) {
        return servers.get(serverId);
    }

    @Override
    public boolean initialize() {
        servers = new ConcurrentHashMap<>();
        List<ServerClusterInfo> serverClusterInfos = serverClusterMapping.selectList(null);
        if (serverClusterInfos != null) {
            for (ServerClusterInfo info : serverClusterInfos) {
                if (info.getServerType() == ServerType.WebServer.ordinal()) {

                    ServerMessageHandler serverMessageHandler = new ServerMessageHandler();
                    ServerConnector connector = new ServerConnector(info.getPort(), info.getInnerIp(), serverMessageHandler, NettyCommonCodecFactory.class);
                    if (connector.connect()) {
                        connector.setServerId(info.getId());
                        CommonMessage message = new CommonMessage((short) Protocol.LOGIN);
                        LoginMsg.ServerLoginMsg.Builder builder = LoginMsg.ServerLoginMsg.newBuilder();
                        builder.setServerId(serverConfig.getWorkerId());
                        message.setBody(builder.build().toByteArray());
                        connector.send(message);
                        servers.put(info.getId(), connector);
                        LOGGER.error("server:" + info + " start success");
                    } else {
                        LOGGER.error("server:" + info + " start fail");
//                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean start() {
//        for (NettyServerConnector connector : bridgeClients) {
//            if (connector.connect())
//                return false;
//        }
        return true;
    }

    @Override
    public void stop() {
        for (NettyServerConnector connector : servers.values()) {
            connector.disconnect();
        }
    }

    @Override
    public boolean reload() {
        stop();
        return start();
    }

    public void removeBridgeClient(ServerConnector serverConnector) {
        servers.remove(serverConnector.getServerId());
    }
}

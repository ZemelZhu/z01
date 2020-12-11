package com.zemel.webserver.controller;

import com.zemel.data.proto.entiy.LoginMsg;
import com.zemel.framework.socket.net.CommonMessage;
import com.zemel.framework.socket.net.ServerClient;
import com.zemel.webserver.component.RedisComponent;
import com.zemel.webserver.component.TimerComponent;
import com.zemel.webserver.component.WebServerComponent;
import com.zemel.webserver.component.ZookeeperComponent;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @Author: zemel
 * @Date: 2020/2/8 13:41
 */
@RestController
@RequestMapping("/test")
public class TestController {
    static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private TimerComponent timerComponent;
    @Autowired
    private WebServerComponent webServerComponent;
   // @Autowired
    private RedisComponent redisComponent;
    @Autowired(required = false)
    private ZookeeperComponent zookeeperComponent;
    @RequestMapping("testRPC")
    public String testRPC()
    {
        Collection<ServerClient> allServer = webServerComponent.getAllServer();
        CommonMessage commonMessage = new CommonMessage((short) 1);
        LoginMsg.PlayerInfoPB.Builder pb = LoginMsg.PlayerInfoPB.newBuilder();
        pb.setType(LoginMsg.LoginCmdType.Error);
        commonMessage.setBody(pb.build().toByteArray());

        CommonMessage commonMessage2 = new CommonMessage((short) 1);
        LoginMsg.PlayerInfoPB.Builder pb2 = LoginMsg.PlayerInfoPB.newBuilder();
        pb2.setType(LoginMsg.LoginCmdType.NewFriend);
        commonMessage2.setBody(pb2.build().toByteArray());
        for (ServerClient serverClient : allServer) {

            LOGGER.error(serverClient+"");
            serverClient.getClientConnection().send(commonMessage);
            serverClient.getClientConnection().send(commonMessage2);
        }
        return "testRPC";
    }
    @RequestMapping("testTimer")
    public String testTimer()
    {
        new Job() {
            @Override
            public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

            }
        }.getClass();
        timerComponent.addDelayJob(this.getClass().getName(),  new Job() {
            @Override
            public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
                LOGGER.error("log job"+cout++);
            }
        }.getClass(),  1000, -1,  1000);
        return "testTimer";
    }
    private int cout = 0;
    class testjob implements Job
    {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            LOGGER.error("log job"+cout++);
        }
    }
    @RequestMapping("testunityrequest")
    public String testunityrequest(int playerID,String password)
    {
        LOGGER.error("playerID:"+playerID+"  password:"+password);
        return "testunityrequest";
    }
    @RequestMapping("testServlet")
    public String testServlet(HttpServletRequest request)
    {

        return "testunityrequest";
    }
    @RequestMapping("testRedisSet")
    public String testRedisSet(String value)
    {
        if(redisComponent.set("haha",value))
            return "success";
        else
            return "fail";
    }
    @RequestMapping("testRedisGet")
    public String testRedisGet()
    {
        System.out.println("SD3333F");
        return (String) redisComponent.get("haha");
    }
    @RequestMapping("testZookeeper")
    public void testZookeeper(int id)
    {
        zookeeperComponent.testLock(id);
    }

}

package com.zemel.framework.sdk;

import com.zemel.framework.until.StackMessagePrint;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author: zemel
 * @Date: 2020/8/6 21:53
 */
@Component
public class WeChatNotifySdk {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatNotifySdk.class);
    @Autowired
    private RestTemplate restTemplate;

    public void notifyException(Exception e) {
        String message = StackMessagePrint.printErrorTrace(e);
    }

    public void notify(String message) {
        String substring = message;
        if (message.length() > 500) {
            substring = message.substring(0, 500);
        }
        String url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=8cfab4e1-369e-4c55-ae90-ba666f2ba59c";
        WeChatNotifyBo bo = new WeChatNotifyBo(substring);
        ResponseEntity<Map> mapResponseEntity = restTemplate.postForEntity(url, bo, Map.class);
        LOGGER.error(mapResponseEntity.getBody() + "");
    }

    public void test() {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=8cfab4e1-369e-4c55-ae90-ba666f2ba59c";
        WeChatNotifyBo bo = new WeChatNotifyBo("sdsd");
        restTemplate.postForEntity(url, bo, Map.class);

    }

    @Data
    private class WeChatNotifyBo {
        private String msgtype;
        private WeChatNotifyInfo text;

        public WeChatNotifyBo(String message) {
            this.msgtype = "text";
            this.text = new WeChatNotifyInfo(message);
        }
    }

    @Data
    private class WeChatNotifyInfo {
        private String content;

        public WeChatNotifyInfo(String content) {
            this.content = content;
        }
    }
}

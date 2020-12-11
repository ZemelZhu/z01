package com.zemel.mallserver.controller.sdk;

import com.zemel.data.type.DataStatus;
import com.zemel.data.type.Roles;
import com.zemel.framework.until.RequestUtil;
import com.zemel.framework.until.ServletUtil;
import com.zemel.framework.until.StringUtil;
import com.zemel.framework.until.TokenUtil;
import com.zemel.framework.vo.ResponseVo;
import com.zemel.mallserver.ao.WeChatLoginAo;
import com.zemel.mallserver.bo.WeChatLoginRpcBo;
import com.zemel.mallserver.config.WeChatLoginConfig;
import com.zemel.mallserver.entiy.MallUserInfo;
import com.zemel.mallserver.mapper.MallUserInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

/**
 * @Author: zemel
 * @Date: 2020/4/4 14:00
 */
@RestController
@RequestMapping("sdk/weChat")
public class WeChatSdkController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatSdkController.class);
    @Autowired
    private WeChatLoginConfig weChatLoginConfig;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MallUserInfoMapper mallUserInfoMapper;

    @PostMapping("/login")
    public ResponseVo user_login(@RequestBody WeChatLoginAo weChatLoginAo, HttpServletRequest request) {

        // 发送请求
        String errorMessage;
        try {
            String url = String.format(weChatLoginConfig.getLoginUrl(), weChatLoginConfig.getAppId(),
                    weChatLoginConfig.getSecret(), weChatLoginAo.getCode());
            WeChatLoginRpcBo WeChatLoginRpcBo = restTemplate.getForObject(url, WeChatLoginRpcBo.class);
            LOGGER.error(WeChatLoginRpcBo+"");
            if (WeChatLoginRpcBo != null && !StringUtil.isNullOrEmpty(WeChatLoginRpcBo.getOpenid())) {
                String openid = WeChatLoginRpcBo.getOpenid();
                HashMap<String, String> map = new HashMap<>();
                map.put("union_id", openid);
                MallUserInfo mallUserInfos = mallUserInfoMapper.selectByUnionId(openid);
                if (mallUserInfos == null) {
                    mallUserInfos = new MallUserInfo();
                    mallUserInfos.setCreateTime(new Date());
                    mallUserInfos.setUnionId(openid);
                    mallUserInfos.setStatus(DataStatus.NORMAL.ordinal());
                    weChatLoginAo.updateMallUserInfo(mallUserInfos);
                    mallUserInfoMapper.insert(mallUserInfos);
                } else {
                    weChatLoginAo.updateMallUserInfo(mallUserInfos);
                    mallUserInfoMapper.updateById(mallUserInfos);
                }
                String requestIP = ServletUtil.getRequestIP(request);
                RequestUtil.loginLog("weChat login success mallUserInfos:" + mallUserInfos.getId() + "  ip:" + requestIP);
                String sign = TokenUtil.sign(mallUserInfos.getId(), Roles.USER);
                return ResponseVo.build(sign);
            }
            else
            {
                LOGGER.error("config:"+weChatLoginConfig);
            }
            errorMessage = "请求微信服务器失败";

        } catch (Exception e) {
            e.printStackTrace();
            errorMessage = e.getMessage();
        }
        RequestUtil.loginLog("weChat login error weChatLoginAo" + weChatLoginAo + "  error message:" + errorMessage);
        return ResponseVo.buildFail(null, errorMessage);
    }

}

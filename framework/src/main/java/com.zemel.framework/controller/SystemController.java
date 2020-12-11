package com.zemel.framework.controller;

import com.zemel.framework.exception.TipException;
import com.zemel.framework.until.ServletUtil;
import com.zemel.framework.until.StringUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zemel
 * @Date: 2020/8/8 22:42
 */
@RestController
@RequestMapping("system")
public class SystemController {
    private static final String mySecret = "sds";

    @PostMapping("close")
    public void close() {
        check();
        System.exit(0);
    }

    private void check() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (!ServletUtil.IsIntranet(request))
            throw new TipException("无法访问");
        String secret = request.getParameter("secret");
        if (StringUtil.isNullOrEmpty(secret))
            throw new TipException("没有秘钥");
        if (!secret.equals(mySecret))
            throw new TipException("秘钥错误");
    }
}

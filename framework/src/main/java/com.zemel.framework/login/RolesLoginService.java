package com.zemel.framework.login;

import com.zemel.data.type.Roles;
import com.zemel.framework.annotation.RolesAnnotation;
import com.zemel.framework.dto.LoginErrorDto;
import com.zemel.framework.exception.TipException;
import com.zemel.framework.timer.IDailyTimer;
import com.zemel.framework.until.RequestUtil;
import com.zemel.framework.until.ServletUtil;
import com.zemel.framework.until.StringUtil;
import com.zemel.framework.until.TokenUtil;
import com.zemel.framework.vo.ResponseVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zemel
 * @Date: 2020/5/4 14:13
 */
@Component
public class RolesLoginService implements IDailyTimer {
    private static final int dailyErrorCount = 3;
    private static final int dailyResourceCount = 200;
    private Map<String, LoginErrorDto> loginCount = new ConcurrentHashMap<>();

    public ResponseVo verity(String token, RolesAnnotation annotation) {
        if (StringUtil.isNullOrEmpty(token))
            return ResponseVo.buildFail(null, "缺失登录凭证");
        ResponseVo response = null;
        Roles type = Roles.USER;
        if (annotation != null)
            type = annotation.roles();
        try {
            if (!TokenUtil.verity(token, type.getSecret())) {
                response = ResponseVo.buildFail(null, "登录过期,请重新登录");
            }
        } catch (Exception e) {

        }
        return response;
    }

    public void loginWithPasswordError(String requestIP, String id) {
        LoginErrorDto loginErrorDto = getErrorDto(requestIP);
        loginErrorDto.setErrorAllCount(loginErrorDto.getErrorAllCount() + 1);
        Map<String, Integer> roleCount = loginErrorDto.getRoleCount();
        Integer count = roleCount.getOrDefault(id, 0) + 1;
        roleCount.put(id, count);
        int remainCount = dailyErrorCount - count;
        throw new TipException("密码错误,剩余输入错误次数为:" + remainCount);
    }

    public void checkLogin(String requestIP, String id) {
        LoginErrorDto loginErrorDto = loginCount.get(requestIP);
        if (loginErrorDto == null)
            return;
        Map<String, Integer> roleCount = loginErrorDto.getRoleCount();
        Integer orDefault = roleCount.getOrDefault(id, 0);
        if (orDefault >= dailyErrorCount)
            throw new TipException("一天只能输错三次密码");
    }

    @Override
    public void dailyJob() {
        loginCount.clear();
    }

    private LoginErrorDto getErrorDto(String requestIP) {
        LoginErrorDto loginErrorDto = loginCount.get(requestIP);
        if (loginErrorDto == null) {
            loginErrorDto = new LoginErrorDto(requestIP, 0, new HashMap<>());
            loginCount.put(requestIP, loginErrorDto);
        }
        return loginErrorDto;
    }

    public void checkResource(HttpServletRequest request) {
        String requestIP = ServletUtil.getRequestIP(request);
        String token = RequestUtil.getToken(request);
        LoginErrorDto loginErrorDto = getErrorDto(requestIP);
        Map<String, Integer> roleCount = loginErrorDto.getRoleCount();
        int count = roleCount.getOrDefault(token, 0) + 1;
        if (count > dailyResourceCount)
            throw new TipException("今日资源操作已达上限");
        roleCount.put(token, count);
    }
}

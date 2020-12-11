package com.zemel.web_framework.utils;

import com.zemel.framework.ComponentManager;
import com.zemel.framework.exception.TipException;
import com.zemel.web_framework.component.LoginComponent;
import com.zemel.web_framework.security.JwtAuthenticatioToken;
import com.zemel.web_framework.security.JwtUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * Security相关操作
 *
 * @author Louis
 * @date Jun 29, 2019
 */
public class SecurityUtils {

    /**
     * 系统登录认证
     * @param request
     * @param username
     * @param password
     * @param authenticationManager
     * @return
     */
    public static JwtAuthenticatioToken login(HttpServletRequest request, String username, String password, AuthenticationManager authenticationManager) {
        JwtAuthenticatioToken token = new JwtAuthenticatioToken(username, password);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 执行登录认证过程
        Authentication authentication = authenticationManager.authenticate(token);
        // 认证成功存储认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌并返回给客户端
        token.setToken(JwtTokenUtils.generateToken(authentication));
        return token;
    }

    /**
     * 获取令牌进行认证
     * @param request
     */
    public static void checkAuthentication(HttpServletRequest request) {
        // 获取令牌并根据令牌获取登录认证信息
        Authentication authentication = JwtTokenUtils.getAuthenticationeFromToken(request);
        // 设置登录认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 获取当前用户名
     * @return
     */
    public static String getUsername() {
        String username = null;
        Authentication authentication = getAuthentication();
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }else if(principal instanceof String)
                return (String) principal;
        }
        return username;
    }
    public static int getUserId0() {
        int userId = 0;
        Authentication authentication = getAuthentication();
        if(authentication != null) {
            Object details1 = authentication.getDetails();
            if(details1!=null&&details1 instanceof JwtUserDetails)
            {
                JwtUserDetails details = (JwtUserDetails) details1;
                return details.getId();
            }
            Object principal = authentication.getPrincipal();
            if(principal != null && principal instanceof UserDetails) {
                userId =  ((JwtUserDetails) principal).getId();
            }else if(principal instanceof Integer)
                userId =  (int) principal;
            else if (principal instanceof String)
            {
                try {
                    userId = Integer.valueOf((String) principal);
                }catch (Exception e)
                {
                    if(authentication instanceof JwtAuthenticatioToken)
                        userId = ComponentManager.getInstance().getComponent(LoginComponent.class).findUserIdByToken(((JwtAuthenticatioToken)authentication).getToken());

                }
            }
        }
        return userId;
    }
    public static int getUserId() {
        int userId = getUserId0();
        if(userId==0)
            throw new TipException("找不到账号，请重新登录",true);
        return userId;
    }
    /**
     * 获取用户名
     * @return
     */
    public static String getUsername(Authentication authentication) {
        String username = null;
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 获取当前登录信息
     * @return
     */
    public static Authentication getAuthentication() {
        if(SecurityContextHolder.getContext() == null) {
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

}

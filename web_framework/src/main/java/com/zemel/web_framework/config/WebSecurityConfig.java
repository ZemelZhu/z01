package com.zemel.web_framework.config;

import com.zemel.web_framework.handler.AjaxAccessDeniedHandler;
import com.zemel.web_framework.handler.AjaxAuthenticationEntryPoint;
import com.zemel.web_framework.handler.AjaxLogoutSuccessHandler;
import com.zemel.web_framework.security.JwtAuthenticationFilter;
import com.zemel.web_framework.security.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IngotConfig ingotConfig;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder  auth) throws Exception {
        // 使用自定义登录身份认证组件
        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> ingot = ingotConfig.getIngot();
        String[] strings = ingot.toArray(new String[ingot.size()]);
        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.cors().and().csrf().disable()
        .authorizeRequests()
                // 跨域预检请求
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // 登录URL
                .antMatchers("/login","/register").permitAll()
                // swagger
                .antMatchers("/swagger**/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/image/**").permitAll()
                .antMatchers(strings).permitAll()
               // .antMatchers("/download/**").permitAll()
                // 其他所有请求需要身份认证
                .anyRequest().authenticated();
        // 退出登录处理器
        http.logout().logoutSuccessHandler(new AjaxLogoutSuccessHandler());
        // 开启登录认证流程过滤器
    //    http.addFilterBefore(new JwtLoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
        // 访问控制时登录状态检查过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
        http.httpBasic().authenticationEntryPoint(new AjaxAuthenticationEntryPoint());
       // http.formLogin().successHandler(new AjaxAuthenticationSuccessHandler()).failureHandler(new AjaxAuthenticationFailureHandler()).permitAll();
        http.exceptionHandling().accessDeniedHandler(new AjaxAccessDeniedHandler ());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
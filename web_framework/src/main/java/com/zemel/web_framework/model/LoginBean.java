package com.zemel.web_framework.model;

import com.zemel.framework.exception.TipException;
import com.zemel.framework.until.StringUtil;

/**
 * 登录接口封装对象
 * @author Louis
 * @date Nov 28, 2018
 */
public class LoginBean {

	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    public void check() {
        if (StringUtil.isNullOrEmpty(username))
            throw new TipException("账号不能为空");
        if (username.length() < 3)
            throw new TipException("账号不能太短");
        if (StringUtil.isNullOrEmpty(password))
            throw new TipException("密码不能为空");
        if (password.length() < 3)
            throw new TipException("密码不能太短");
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

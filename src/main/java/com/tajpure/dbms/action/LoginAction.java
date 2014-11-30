package com.tajpure.dbms.action;

import java.util.Map;

import javax.servlet.http.HttpServlet;

import com.opensymphony.xwork2.ActionContext;
import com.tajpure.utils.LoggerUtil;

public class LoginAction extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() {
		Map<String, Object> map = ActionContext.getContext().getSession();
		map.put("name", username);
		LoggerUtil.warn(username+" login successful.");
		return "success";
	}
	
	public String username;
	
	public String password;

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
	
}

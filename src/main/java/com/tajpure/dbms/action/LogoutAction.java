package com.tajpure.dbms.action;

import java.util.Map;

import javax.servlet.http.HttpServlet;

import com.opensymphony.xwork2.ActionContext;

public class LogoutAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public String execute() {
		Map<String, Object> map = ActionContext.getContext().getSession();
		map.clear();
		return "success";
	}
}

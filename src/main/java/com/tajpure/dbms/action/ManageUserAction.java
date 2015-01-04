package com.tajpure.dbms.action;

import java.util.Map;

import javax.servlet.http.HttpServlet;

import com.opensymphony.xwork2.ActionContext;
import com.tajpure.dbms.entity.User;

public class ManageUserAction  extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public String execute() {
		Map<String, Object> map = ActionContext.getContext().getSession();
		user = (User) map.get("user");
		return "success";
	}
	
	public String insert() {
		return "";
	}
	
	public String update() {
		return "";
	}
	
	public String delete() {
		return "";
	}
	
	private User user = new User();
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

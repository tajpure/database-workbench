package com.tajpure.dbms.action;

import javax.servlet.http.HttpServlet;

public class CommondAction extends HttpServlet {

	private static final long serialVersionUID = 2606592490491476226L;

	public String execute() {
		
		return "success";
	}

	private String commond;
	
	public String getCommond() {
		return commond;
	}

	public void setCommond(String commond) {
		this.commond = commond;
	}
}

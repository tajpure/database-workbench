package com.tajpure.dbms.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class ManageTableAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public String execute() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print("");
		out.close();
		return null;
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
}

package com.tajpure.dbms.action;

import javax.servlet.http.HttpServlet;

/**
 * Error page
 * @author taojx
 *
 */
public class ErrorAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public String execute() {
		return "success";
	}
	
	private String errorInfo;
	
	private String returnUrl;

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

}

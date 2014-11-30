package com.tajpure.dbms.action;

import javax.servlet.http.HttpServlet;

/**
 * 错误页面，提示相应的错误信息
 * @author taojx
 *
 */
public class ErrorPageAction extends HttpServlet {
	
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

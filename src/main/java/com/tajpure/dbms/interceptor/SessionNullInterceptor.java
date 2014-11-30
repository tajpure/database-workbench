package com.tajpure.dbms.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 
 * @author taojx
 * 
 */
public class SessionNullInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;

	private boolean debug = false;

	public void destroy() {

	}

	public void init() {

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext()
				.getSession();
		if (debug == true) {
			return invocation.invoke();
		} else {
			if (session.get("name") == null) {
				return "login";
			} else {
				return invocation.invoke();
			}
		}
	}
}

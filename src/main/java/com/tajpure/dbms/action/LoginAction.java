package com.tajpure.dbms.action;

import java.util.Map;

import javax.servlet.http.HttpServlet;

import com.opensymphony.xwork2.ActionContext;
import com.tajpure.dbms.database.Database;
import com.tajpure.dbms.entity.User;
import com.tajpure.dbms.util.Assert;
import com.tajpure.dbms.util.ConnectionPool;
import com.tajpure.dbms.util.LoggerUtil;

public class LoginAction extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	public String execute() {
		Map<String, Object> map = ActionContext.getContext().getSession();
		LoggerUtil.warn(username+" login successful.");
		System.out.println(username + " " + password + " " + typeOfDB);
		Database db = null;
		switch(typeOfDB) {
			case 0 : db = Database.MySQL; break;
			case 1 : db = Database.SQLServer; break;
			case 2 : db = Database.Oracle; break;
			default : Assert.error("This kind of database isn't supported.");
		}
		User user = new User(username, password, db);
		
		if (!ConnectionPool.isUserLegal(user)) {
			return "failure";
		}
		
		map.put("name", user);
		
		return "success";
	}
	
	public String username;
	
	public String password;
	
	private int typeOfDB = 0;

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

	public int getTypeOfDB() {
		return typeOfDB;
	}

	public void setTypeOfDB(int typeOfDB) {
		this.typeOfDB = typeOfDB;
	}
	
}

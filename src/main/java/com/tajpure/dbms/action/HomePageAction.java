package com.tajpure.dbms.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import com.opensymphony.xwork2.ActionContext;
import com.tajpure.dbms.database.DatabaseMetaDataFactory;
import com.tajpure.dbms.database.DatabaseMetaDataWorker;
import com.tajpure.dbms.entity.Schema;
import com.tajpure.dbms.entity.User;

public class HomePageAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public String execute() {
		Map<String, Object> map = ActionContext.getContext().getSession();
		DatabaseMetaDataFactory factory = DatabaseMetaDataFactory.getInstance();
		User user = (User) map.get("user");
		DatabaseMetaDataWorker worker = factory.getWorker(user);
		
		schemas = worker.getSchemas();
		
		return "success";
	}

	private List<Schema> schemas = null;
	
	public List<Schema> getSchemas() {
		return schemas;
	}

	public void setSchemas(List<Schema> schemas) {
		this.schemas = schemas;
	}
}

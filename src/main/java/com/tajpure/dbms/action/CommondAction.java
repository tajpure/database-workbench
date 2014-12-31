package com.tajpure.dbms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.tajpure.dbms.database.DatabaseMetaDataFactory;
import com.tajpure.dbms.database.DatabaseMetaDataWorker;
import com.tajpure.dbms.util.SQLUtil;

public class CommondAction extends HttpServlet {

	private static final long serialVersionUID = 2606592490491476226L;

	private DatabaseMetaDataFactory factory = DatabaseMetaDataFactory.getInstance();
	
	public String execute() {
		DatabaseMetaDataWorker worker = factory.getWorker();
		String[] sqlArr = parseCommond(commond);
		for (String SQL : sqlArr) {
			executeSQL(SQL, worker);
		}
		worker.drop();
		return null;
	}
	
	public void executeSQL(String SQL, DatabaseMetaDataWorker worker) {
		String result = null;
		if (SQLUtil.isSeletedSQL(SQL)) {
			List<List<Object>> objList = worker.executeQueryCommond(SQL, schemaName);
			result = ListToHtml(objList);
		} else {
			int rs = worker.executeCommond(SQL, schemaName);
			result = rs + "";
		}
		sendResponse(result);
	}
	
	public String[] parseCommond(String commond) {
		return commond.split(";");
	}
	
	public String ListToHtml(List<List<Object>> objList) {
		StringBuilder result = new StringBuilder();
		
		return result.toString();
	}
	
	public void sendResponse(String message) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(message);
		out.close();
	}


	private String commond;
	
	private String schemaName;
	
	public String getCommond() {
		return commond;
	}

	public void setCommond(String commond) {
		this.commond = commond;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
}

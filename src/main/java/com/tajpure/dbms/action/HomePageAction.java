package com.tajpure.dbms.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import com.opensymphony.xwork2.ActionContext;
import com.tajpure.dbms.database.DatabaseMetaDataFactory;
import com.tajpure.dbms.database.DatabaseMetaDataWorker;
import com.tajpure.dbms.entity.Schema;
import com.tajpure.dbms.entity.Table;
import com.tajpure.dbms.entity.User;

public class HomePageAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public String execute() {
		Map<String, Object> map = ActionContext.getContext().getSession();
		DatabaseMetaDataFactory factory = DatabaseMetaDataFactory.getInstance();
		User user = (User) map.get("user");
		DatabaseMetaDataWorker worker = factory.getWorker(user);
		
		if (tableName != null) {
			table = worker.getTable(schemaName, tableName);
			values = worker.getValues(user, schemaName, tableName);
		}
		if (schemas == null) {	// TODO Don't update schemas tree
			schemas = worker.getSchemas();
		}
		
		return "success";
	}
	
	private String schemaName = null;
	
	private String tableName = null;
	
	// TODO
	private static List<Schema> schemas = null;
	
	private Table table = null;
	
	private List<List> values = null;
	
	public List<Schema> getSchemas() {
		return schemas;
	}

	public void setSchemas(List<Schema> schemas) {
		this.schemas = schemas;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public List<List> getValues() {
		return values;
	}

	public void setValues(List<List> values) {
		this.values = values;
	}
}

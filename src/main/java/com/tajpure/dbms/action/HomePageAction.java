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
	
	private static final int rowsPerPage = 24;

	public String execute() {
		String tableName = curTable.getName();
		String schemaName = curTable.getItsSchema();
		Map<String, Object> map = ActionContext.getContext().getSession();
		DatabaseMetaDataFactory factory = DatabaseMetaDataFactory.getInstance();
		User user = (User) map.get("user");
		DatabaseMetaDataWorker worker = factory.getWorker(user);
		
		if (tableName != null) {
			curTable = worker.getTable(schemaName, tableName);
			values = worker.getValuesByPage(user, curTable, page, rowsPerPage);
			if(totalPages == 0) {
				totalPages = worker.getValuesTotalPages(user, curTable, rowsPerPage);
			}
		}
		if (schemas == null) {	// TODO Don't update schemas tree
			schemas = worker.getSchemas();
		}
		worker.drop();
		return "success";
	}

	private int page = 1;
	
	private int totalPages = 0;
	
	private Table curTable = new Table();
	
	private List<Schema> schemas = null;
	
	private List<List<Object>> values = null;
	
	public List<Schema> getSchemas() {
		return schemas;
	}

	public void setSchemas(List<Schema> schemas) {
		this.schemas = schemas;
	}

	public List<List<Object>> getValues() {
		return values;
	}

	public void setValues(List<List<Object>> values) {
		this.values = values;
	}

	public Table getCurTable() {
		return curTable;
	}

	public void setCurTable(Table curTable) {
		this.curTable = curTable;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}

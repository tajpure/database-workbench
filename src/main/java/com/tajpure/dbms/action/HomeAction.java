package com.tajpure.dbms.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import com.tajpure.dbms.database.DatabaseMetaDataFactory;
import com.tajpure.dbms.database.DatabaseMetaDataWorker;
import com.tajpure.dbms.entity.Column;
import com.tajpure.dbms.entity.Schema;
import com.tajpure.dbms.entity.Table;

public class HomeAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final int rowsPerPage = 20;
	
	private DatabaseMetaDataFactory factory = DatabaseMetaDataFactory.getInstance();

	public String execute() {
		String tableName = curTable.getName();
		String schemaName = curTable.getItsSchema();
		DatabaseMetaDataWorker worker = factory.getWorker();
		if (tableName != null && page != 0) {
			curTable = worker.getTable(schemaName, tableName);
			values = worker.getValuesByPage(curTable, page, rowsPerPage);
			if(totalPages == 0) {
				totalPages = worker.getValuesTotalPages(curTable, rowsPerPage);
			}
		}
		if (schemas == null) {	// TODO Don't update schemas tree
			schemas = worker.getSchemas();
		}
		if (columns == null || columns.size() == 0) {
			columns = worker.getColumns(curTable.getItsSchema(), curTable.getName());
		}
		worker.drop();
		return "success";
	}

	private int page = 1;
	
	private int curTab = 0;
	
	private int totalPages = 0;
	
	private Table curTable = new Table();
	
	private List<Schema> schemas = null;
	
	private List<List<Object>> values = null;

	private List<Column> columns = new ArrayList<Column>();
	
	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
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

	public int getCurTab() {
		return curTab;
	}

	public void setCurTab(int curTab) {
		this.curTab = curTab;
	}
}

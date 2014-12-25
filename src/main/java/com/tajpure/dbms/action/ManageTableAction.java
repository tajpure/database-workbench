package com.tajpure.dbms.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import com.tajpure.dbms.database.DatabaseMetaDataFactory;
import com.tajpure.dbms.database.DatabaseMetaDataWorker;
import com.tajpure.dbms.entity.Column;
import com.tajpure.dbms.entity.Schema;
import com.tajpure.dbms.entity.Table;

public class ManageTableAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private DatabaseMetaDataFactory factory = DatabaseMetaDataFactory.getInstance();
	
	public String execute() {
		DatabaseMetaDataWorker worker = factory.getWorker();
		schemas = worker.getSchemas();
		if (columns == null || columns.size() == 0) {
			columns = worker.getColumns(curTable.getItsSchema(), curTable.getName());
		}
		worker.drop();
		return "success";
	}
	
	public String updateColumns() {
		DatabaseMetaDataWorker worker = factory.getWorker();
		schemas = worker.getSchemas();
		if (columns == null || columns.size() == 0) {
			columns = worker.getColumns(curTable.getItsSchema(), curTable.getName());
		}
		worker.drop();
		return "success";
	}
	
	public String insertColumn() {
		return "";
	}
	
	public String deleteColumns() {
		return "";
	}
	
	private List<Schema> schemas = null;
	
	private Table curTable = new Table();
	
	private List<Column> columns = new ArrayList<Column>();
	
	private int page;
	
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

	public List<Schema> getSchemas() {
		return schemas;
	}

	public void setSchemas(List<Schema> schemas) {
		this.schemas = schemas;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

}

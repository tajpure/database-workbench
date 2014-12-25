package com.tajpure.dbms.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import com.tajpure.dbms.database.DatabaseMetaDataFactory;
import com.tajpure.dbms.database.DatabaseMetaDataWorker;
import com.tajpure.dbms.entity.Column;
import com.tajpure.dbms.entity.Table;

public class ManageTableAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private DatabaseMetaDataFactory factory = DatabaseMetaDataFactory.getInstance();
	
	public String execute() {
		return "";
	}
	
	public String updateTables() {
		DatabaseMetaDataWorker worker = factory.getWorker();
		if (columns == null || columns.size() == 0) {
			columns = worker.getColumns(curTable.getItsSchema(), curTable.getName());
		}
		worker.drop();
		return "success";
	}
	
	public String insertTables() {
		DatabaseMetaDataWorker worker = factory.getWorker();
		if (insertColumn.getName() != null) {
			worker.insertColumn(curTable, insertColumn);
		}
		worker.drop();
		return "success";
	}
	
	public String deleteTables() {
		return "";
	}
	
	private int curTab = 1;
	
	private Table curTable = new Table();
	
	private List<Column> columns = new ArrayList<Column>();
	
	private Column insertColumn = new Column();
	
	public Table getCurTable() {
		return curTable;
	}

	public void setCurTable(Table curTable) {
		this.curTable = curTable;
	}
	
	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public Column getInsertColumn() {
		return insertColumn;
	}

	public void setInsertColumn(Column insertColumn) {
		this.insertColumn = insertColumn;
	}

	public int getCurTab() {
		return curTab;
	}

	public void setCurTab(int curTab) {
		this.curTab = curTab;
	}

}

package com.tajpure.dbms.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import com.tajpure.dbms.database.DatabaseMetaDataFactory;
import com.tajpure.dbms.database.DatabaseMetaDataWorker;
import com.tajpure.dbms.entity.Column;
import com.tajpure.dbms.entity.Table;

public class ManageColumnAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private DatabaseMetaDataFactory factory = DatabaseMetaDataFactory.getInstance();

	public String updateColumns() {
		DatabaseMetaDataWorker worker = factory.getWorker();
		if (columns == null || columns.size() == 0) {
			 worker.updateColumns(curTable, columns, columns);
		}
		worker.drop();
		return "success";
	}
	
	public String insertColumn() {
		DatabaseMetaDataWorker worker = factory.getWorker();
		if (insertColumn.getName() != null) {
			worker.insertColumn(curTable, insertColumn);
		}
		worker.drop();
		return "success";
	}
	
	public String deleteColumns() {
		DatabaseMetaDataWorker worker = factory.getWorker();
		if (insertColumn.getName() != null) {
			worker.deleteColumns(curTable, columns);
		}
		worker.drop();
		return "success";
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

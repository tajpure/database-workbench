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
		System.out.println(oldColumns);
		System.out.println(newColumns);
		DatabaseMetaDataWorker worker = factory.getWorker();
		if (columns == null || columns.size() == 0) {
			 worker.updateColumns(curTable, oldColumns, newColumns);
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
			worker.deleteColumns(curTable, getDeleteColumn(delIndexStr));
		}
		worker.drop();
		return "success";
	}
	
	public List<Column> getDeleteColumn(String delIndexStr) {
		List<Column> delColumns = new ArrayList<Column>();
		String[] indexArr = getIndexArr(delIndexStr);
		for (String index : indexArr) {
			delColumns.add(newColumns.get(Integer.parseInt(index)));
		}
		return delColumns;
	}
	
	private String[] getIndexArr(String indexStr) {
		if (indexStr == null || "".equals(indexStr)) return null;
		String[] indexArr = indexStr.split("o");
		return indexArr;
	}
	
	private int curTab = 1;
	
	private Table curTable = new Table();
	
	private List<Column> columns = new ArrayList<Column>();
	
	private List<Column> newColumns = new ArrayList<Column>();
	
	private List<Column> oldColumns = new ArrayList<Column>();
	
	private Column insertColumn = new Column();
	
	private String delIndexStr;
	
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

	public List<Column> getNewColumns() {
		return newColumns;
	}

	public void setNewColumns(List<Column> newColumns) {
		this.newColumns = newColumns;
	}

	public List<Column> getOldColumns() {
		return oldColumns;
	}

	public void setOldColumns(List<Column> oldColumns) {
		this.oldColumns = oldColumns;
	}

	public String getDelIndexStr() {
		return delIndexStr;
	}

	public void setDelIndexStr(String delIndexStr) {
		this.delIndexStr = delIndexStr;
	}

}

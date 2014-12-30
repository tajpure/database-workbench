package com.tajpure.dbms.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import com.tajpure.dbms.database.DatabaseMetaDataFactory;
import com.tajpure.dbms.database.DatabaseMetaDataWorker;
import com.tajpure.dbms.entity.Column;
import com.tajpure.dbms.entity.Table;

/**
 * TODO To support boolean fields
 * @author tajpure
 *
 */
public class ManageTableAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private DatabaseMetaDataFactory factory = DatabaseMetaDataFactory.getInstance();
	
	public String execute() {
		return "";
	}
	
	public String updateTables() {
		return "success";
	}
	
	public String createTable() {
		DatabaseMetaDataWorker worker = factory.getWorker();
		newTable.setColumns(insertColumns);
		curTable.setName(newTable.getName());
		curTable.setItsSchema(newTable.getItsSchema());
		worker.createTable(newTable);
		worker.drop();
		return "success";
	}
	
	public String deleteTables() {
		return "success";
	}
	
	private int curTab = 1;
	
	private Table curTable = new Table();
	
	private Table newTable = new Table();
	
	private List<Table> newTables = new ArrayList<Table>();

	private List<Table> oldTables = new ArrayList<Table>();
	
	private List<Column> insertColumns = new ArrayList<Column>();
	
	public Table getCurTable() {
		return curTable;
	}

	public void setCurTable(Table curTable) {
		this.curTable = curTable;
	}

	public int getCurTab() {
		return curTab;
	}

	public void setCurTab(int curTab) {
		this.curTab = curTab;
	}

	public Table getNewTable() {
		return newTable;
	}

	public void setNewTable(Table newTable) {
		this.newTable = newTable;
	}

	public List<Column> getInsertColumns() {
		return insertColumns;
	}

	public void setInsertColumns(List<Column> insertColumns) {
		this.insertColumns = insertColumns;
	}

	public List<Table> getNewTables() {
		return newTables;
	}

	public void setNewTables(List<Table> newTables) {
		this.newTables = newTables;
	}

	public List<Table> getOldTables() {
		return oldTables;
	}

	public void setOldTables(List<Table> oldTables) {
		this.oldTables = oldTables;
	}

}

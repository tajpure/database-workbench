package com.tajpure.dbms.entity;

import java.sql.Types;

public class Column {
	
	private String name;
	
	private int dataType;
	
	private int columnSize;
	
	private String itsTable;
	
	private String itsSchema;
	
	private String itsCatalog;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public String getItsTable() {
		return itsTable;
	}

	public void setItsTable(String itsTable) {
		this.itsTable = itsTable;
	}

	public String getItsSchema() {
		return itsSchema;
	}

	public void setItsSchema(String itsSchema) {
		this.itsSchema = itsSchema;
	}

	public String getItsCatalog() {
		return itsCatalog;
	}

	public void setItsCatalog(String itsCatalog) {
		this.itsCatalog = itsCatalog;
	}
	
	public String toString() {
		return name + " " + dataType + " " + " " + columnSize;
	}
}

package com.tajpure.dbms.entity;

import java.util.List;

public class Table {
	
	public Table() {
		
	}
	
	public Table(String name, String itsSchema) {
		this.name = name;
		this.itsSchema = itsSchema;
	}
	
	private String name;
	
	private List<Column> columns;
	
	// The schema that the table belong to.
	private String itsSchema;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	public String toString() {
		return name;
	}

	public String getItsSchema() {
		return itsSchema;
	}

	public void setItsSchema(String itsSchema) {
		this.itsSchema = itsSchema;
	}
}

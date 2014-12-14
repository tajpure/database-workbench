package com.tajpure.dbms.entity;

import java.util.List;

public class Schema {
	
	private String name = null;
	
	private List<Table> tables = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}
}

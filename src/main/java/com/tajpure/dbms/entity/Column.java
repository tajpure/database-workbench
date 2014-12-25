package com.tajpure.dbms.entity;


public class Column {
	
	private String name;
	
	private int dataType;
	
	private int columnSize;

	private String columnDefault;
	
	// Belongs to primary key
	private boolean PK = false;

	// Not Null
	private boolean NN = false;

	// Unique Index
	private boolean UQ = false;

	// Is binary column
	private boolean BIN = false;

	// Insigned data type
	private boolean UN = false;

	// Fill up values for that column with 0's if it is numeric
	private boolean ZF = false;

	// Auto Incremental
	private boolean AI = false;
	
	public boolean isPK() {
		return PK;
	}

	public void setPK(boolean pK) {
		PK = pK;
	}

	public boolean isNN() {
		return NN;
	}

	public void setNN(boolean nN) {
		NN = nN;
	}

	public boolean isUQ() {
		return UQ;
	}

	public void setUQ(boolean uQ) {
		UQ = uQ;
	}

	public boolean isBIN() {
		return BIN;
	}

	public void setBIN(boolean bIN) {
		BIN = bIN;
	}

	public boolean isUN() {
		return UN;
	}

	public void setUN(boolean uN) {
		UN = uN;
	}

	public boolean isZF() {
		return ZF;
	}

	public void setZF(boolean zF) {
		ZF = zF;
	}

	public boolean isAI() {
		return AI;
	}

	public void setAI(boolean aI) {
		AI = aI;
	}

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
	
	public String toString() {
		return name + " " + dataType + " " + " " + columnSize;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}
}

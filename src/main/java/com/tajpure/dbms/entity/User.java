package com.tajpure.dbms.entity;

import com.tajpure.dbms.database.Database;

public class User {

	private String name;
	
	private String password;
	
	private Database database;
	
	public User(String username, String password, Database database) {
		this.name = username;
		this.password = password;
		this.database = database;
	}

	public String getName() {
		return name;
	}

	public void setName(String username) {
		this.name = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}
}

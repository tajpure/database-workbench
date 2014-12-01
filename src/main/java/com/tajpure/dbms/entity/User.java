package com.tajpure.dbms.entity;

import com.tajpure.dbms.database.Database;

public class User {

	private String username;
	
	private String password;
	
	private Database database;
	
	public User(String username, String password, Database database) {
		this.username = username;
		this.password = password;
		this.database = database;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

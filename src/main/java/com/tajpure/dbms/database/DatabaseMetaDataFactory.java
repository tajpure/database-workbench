package com.tajpure.dbms.database;

import java.sql.Connection;

import com.tajpure.dbms.entity.User;
import com.tajpure.dbms.utils.Assert;
import com.tajpure.dbms.utils.ConnectionPool;


public class DatabaseMetaDataFactory {
	
	private static DatabaseMetaDataFactory factory = null;
	
	public DatabaseMetaDataWorker getWorker(User boss) {
		Connection con = ConnectionPool.getConnection(boss);
		DatabaseMetaDataWorker worker = null;
		switch (boss.getDatabase()) {
			case MySQL : worker = new MySQLMetaDataWorker(con); break;
			case SQLServer : worker = new SQLServerMetaDataWorker(con); break;
			case Oracle : worker = new SQLServerMetaDataWorker(con); break;
			default : Assert.error("This kind of database isn't supported.");
		}
		return worker;
	}
	
	public static DatabaseMetaDataFactory getInstance() {
		if (factory == null) {
			factory = new DatabaseMetaDataFactory();
		}
		return factory;
	}
}

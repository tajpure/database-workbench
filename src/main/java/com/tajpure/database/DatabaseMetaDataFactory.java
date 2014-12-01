package com.tajpure.database;

import java.sql.Connection;

import com.tajpure.utils.Assert;
import com.tajpure.utils.ConnectionPool;


public class DatabaseMetaDataFactory {
	
	private static DatabaseMetaDataFactory factory = null;
	
	public DatabaseMetaDataWorker getWorker(Database database) {
		Connection con = ConnectionPool.getConnection();
		DatabaseMetaDataWorker worker = null;
		
		switch (database) {
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

package com.tajpure.database;

import com.tajpure.utils.Assert;


public class DatabaseMetaDataFactory {
	
	private static DatabaseMetaDataFactory factory = null;
	
	public DatabaseMetaDataWorker getDatabaseMetaDataWorker(Database database) {
		DatabaseMetaDataWorker worker = null;
		switch (database) {
		case MySQL : worker = new MySQLMetaDataWorker(); break;
		case SQLServer : worker = new SQLServerMetaDataWorker(); break;
		case Oracle : worker = new SQLServerMetaDataWorker(); break;
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

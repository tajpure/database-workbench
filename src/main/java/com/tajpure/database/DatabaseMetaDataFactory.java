package com.tajpure.database;


public class DatabaseMetaDataFactory {
	
	private static DatabaseMetaDataFactory factory = null;
	
	public DatabaseMetaDataWorker getDatabaseMetaDataWorker(Database database) {
		DatabaseMetaDataWorker worker = null;
		switch (database) {
		case MySQL : worker = new MySQLMetaDataWorker(); break;
		case SQLServer : worker = new SQLServerMetaDataWorker(); break;
		default : worker = null;
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

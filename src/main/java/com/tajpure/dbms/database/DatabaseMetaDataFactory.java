package com.tajpure.dbms.database;

import java.sql.Connection;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.tajpure.dbms.entity.User;
import com.tajpure.dbms.util.Assert;
import com.tajpure.dbms.util.ConnectionPool;


public class DatabaseMetaDataFactory {
	
	private static DatabaseMetaDataFactory factory = null;
	
	public synchronized DatabaseMetaDataWorker getWorker() {
		Map<String, Object> map = ActionContext.getContext().getSession();
		User user = (User) map.get("user");
		return getWorker(user);
	}
	
	public DatabaseMetaDataWorker getWorker(User user) {
		Connection con = ConnectionPool.getConnection(user);
		DatabaseMetaDataWorker worker = null;
		switch (user.getDatabase()) {
			case MySQL : worker = new MySQLMetaDataWorker(con, user); break;
			case SQLServer : worker = new SQLServerMetaDataWorker(con, user); break;
			case Oracle : worker = new OracleMetaDataWorker(con, user); break;
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

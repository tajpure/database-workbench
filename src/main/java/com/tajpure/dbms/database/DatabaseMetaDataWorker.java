package com.tajpure.dbms.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import com.tajpure.dbms.entity.Schema;
import com.tajpure.dbms.entity.Table;
import com.tajpure.dbms.utils.Assert;
import com.tajpure.dbms.utils.ConnectionPool;


/**
 * 
 * @author taojx
 *
 */
public abstract class DatabaseMetaDataWorker {
	
	protected Connection con = null;
	
	protected DatabaseMetaData metaData = null;
	
	public DatabaseMetaDataWorker(Connection con) {
		this.con = con;
		init();
	}
	
	private void init() {
		if (con == null) {
			Assert.error("Connection is null!");
			return;
		}
		try {
			this.metaData = con.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getDriverName() {
		String driverName = null;
		try {
			driverName = metaData.getDriverName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return driverName;
	}
	
	public abstract List<Schema> getSchemas();
	
	public abstract List<Table> getTables(String schemaName);
	
	public abstract Schema getSchema(String schemaName);
	
	public abstract Table getTable(String tableName);
	
	public void drop() {
		ConnectionPool.pushConnectionBackToPool(con);
	}

}

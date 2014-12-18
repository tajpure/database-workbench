package com.tajpure.dbms.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import com.tajpure.dbms.entity.Column;
import com.tajpure.dbms.entity.Schema;
import com.tajpure.dbms.entity.Table;
import com.tajpure.dbms.entity.User;
import com.tajpure.dbms.util.Assert;
import com.tajpure.dbms.util.ConnectionPool;


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
	
	public void drop() {
		ConnectionPool.pushConnectionBackToPool(con);
		con = null;
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
	
	public abstract Schema getSchema(String schemaName);

	public abstract Table getTable(String schemaName, String tableName);
	
	public abstract List<Table> getTables(String schemaName);

	public abstract Column getColumn(String schemaName, String tableName, String columnName);
	
	public abstract List<Column> getColumns(String schemaName, String tableName);
	
	public abstract List<List<Object>> getValues(User user, Table table);
	
	public abstract List<List<Object>> getValuesByPage(User user, Table table, int page, int rowsPerPage);
	
	public abstract int getValuesTotalPages(User user, Table table, int rowsPerPage);
	
	public abstract boolean isSysSchema(String schemaName);

}

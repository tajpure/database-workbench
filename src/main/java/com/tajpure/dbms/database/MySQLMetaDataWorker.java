package com.tajpure.dbms.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tajpure.dbms.entity.Schema;
import com.tajpure.dbms.entity.Table;
import com.tajpure.dbms.entity.User;
import com.tajpure.dbms.utils.ConnectionPool;


public class MySQLMetaDataWorker extends DatabaseMetaDataWorker {

	public MySQLMetaDataWorker(Connection con) {
		super(con);
	}

	@Override
	public Schema getSchema(String schemaName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Schema> getSchemas() {
		List<Schema> schemas = new ArrayList<Schema>();
		
		try {
			ResultSet rs = metaData.getCatalogs();
			while (rs.next()) {
				Schema schema = new Schema();
				schema.setName(rs.getString(1));
				schemas.add(schema);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		System.out.println(schemas);
		return schemas;
	}

	@Override
	public Table getTable(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Table> getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) {
		List<Table> tables = new ArrayList<Table>();
		
		try {
			ResultSet rs = metaData.getTables(catalog, schemaPattern, tableNamePattern, types);
			while (rs.next()) {
				Table table = new Table();
				table.setName(rs.getString(1));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Table> getTables(String schemaName) {
		List<Table> tables = null;
		tables = getTables(null, schemaName, "%", new String[] {"TABLE"});
		return tables;
	}
	
	public static void main(String[] args) {
		User user = new User("google", "google", Database.MySQL);
		Connection con = ConnectionPool.getConnection(user);
		MySQLMetaDataWorker worker = new MySQLMetaDataWorker(con);
		System.out.println(worker.getTables("hoolai_share"));
	}

}

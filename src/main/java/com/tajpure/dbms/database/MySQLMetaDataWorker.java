package com.tajpure.dbms.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tajpure.dbms.entity.Schema;
import com.tajpure.dbms.entity.Table;


public class MySQLMetaDataWorker extends DatabaseMetaDataWorker {

	public MySQLMetaDataWorker(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
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
	public List<Table> getTables(String schemaName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Schema getSchema(String schemaName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Table getTable(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}
	


}

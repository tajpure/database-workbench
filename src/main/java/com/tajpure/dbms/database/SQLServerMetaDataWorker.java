package com.tajpure.dbms.database;

import java.sql.Connection;
import java.util.List;

import com.tajpure.dbms.entity.Schema;
import com.tajpure.dbms.entity.Table;

public class SQLServerMetaDataWorker extends DatabaseMetaDataWorker {

	public SQLServerMetaDataWorker(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Schema> getSchemas() {
		// TODO Auto-generated method stub
		return null;
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

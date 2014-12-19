package com.tajpure.dbms.database;

import java.sql.Connection;
import java.util.List;

import com.tajpure.dbms.entity.Column;
import com.tajpure.dbms.entity.Schema;
import com.tajpure.dbms.entity.Table;
import com.tajpure.dbms.entity.User;

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
	public Table getTable(String schemaName, String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Column getColumn(String schemaName, String tableName,
			String columnName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Column> getColumns(String schemaName, String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Object>> getValues(User user, Table table) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<List<Object>> getValuesByPage(User user, Table table, int page,
			int rowsPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSysSchema(String schemaName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getValuesTotalPages(User user, Table table, int rowsPerPage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertValue(User user, Table table, List<Object> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertValues(User user, Table table, List<List<Object>> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateValues(User user, Table table, List<List<Object>> oldList,
			List<List<Object>> newlist) {
		// TODO Auto-generated method stub
		return 0;
	}

}

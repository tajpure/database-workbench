package com.tajpure.dbms.database;

import java.sql.Connection;
import java.util.List;

import com.tajpure.dbms.entity.Column;
import com.tajpure.dbms.entity.Function;
import com.tajpure.dbms.entity.Schema;
import com.tajpure.dbms.entity.StoredProcedure;
import com.tajpure.dbms.entity.Table;
import com.tajpure.dbms.entity.User;
import com.tajpure.dbms.entity.View;

public class SQLServerMetaDataWorker extends DatabaseMetaDataWorker {

	public SQLServerMetaDataWorker(Connection con, User user) {
		super(con, user);
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
	public List<List<Object>> getValues(Table table) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSysSchema(String schemaName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getValuesTotalPages(Table table, int rowsPerPage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> int insertValue(Table table, List<T> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> int insertValues(Table table, List<List<T>> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> int updateValues(Table table, List<List<T>> oldList,
			List<List<T>> newlist) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> List<List<T>> getValuesByPage(Table table, int page,
			int rowsPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> int deleteValue(Table table, List<List<T>> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<View> getViews(String schemaName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StoredProcedure> getStoredProcedures() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Function> getFunctions() {
		// TODO Auto-generated method stub
		return null;
	}

}

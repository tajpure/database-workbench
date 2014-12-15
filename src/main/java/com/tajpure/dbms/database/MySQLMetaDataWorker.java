package com.tajpure.dbms.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tajpure.dbms.entity.Column;
import com.tajpure.dbms.entity.Schema;
import com.tajpure.dbms.entity.Table;


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
		return schemas;
	}

	@Override
	public Table getTable(String schemaName, String tableName) {
		Table table = null;
		table = getTables(schemaName, "%", tableName, new String[] {"TABLE"}).get(0);
		return table;
	}

	@Override
	public List<Table> getTables(String schemaName) {
		List<Table> tables = null;
		tables = getTables(schemaName, "%", "%", new String[] {"TABLE"});
		return tables;
	}
	
	public List<Table> getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) {
		List<Table> tables = new ArrayList<Table>();
		
		try {
			ResultSet rs = metaData.getTables(catalog, schemaPattern, tableNamePattern, types);
			while (rs.next()) {
				Table table = new Table();
				table.setName(rs.getString(3));
				tables.add(table);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tables;
	}

	@Override
	public Column getColumn(String schemaName, String tableName, String columnName) {
		Column column = null;
		column = getColumns(schemaName, "%", tableName, columnName).get(0);
		return column;
	}

	@Override
	public List<Column> getColumns(String schemaName, String tableName) {
		List<Column> columns = null;
		columns = getColumns(schemaName, "%", tableName, "%");
		return columns;
	}
	
	public List<Column> getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern) {
		List<Column> columns = new ArrayList<Column>();
		try {
			ResultSet rs = metaData.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
			while (rs.next()) {
				Column column = new Column();
				column.setName(rs.getString(4));
				columns.add(column);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columns;
	}

}

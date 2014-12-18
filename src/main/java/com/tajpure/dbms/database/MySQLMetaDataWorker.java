package com.tajpure.dbms.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tajpure.dbms.entity.Column;
import com.tajpure.dbms.entity.Schema;
import com.tajpure.dbms.entity.Table;
import com.tajpure.dbms.entity.User;
import com.tajpure.dbms.util.ConnectionPool;


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
				if (isSysSchema(rs.getString(1))) {
					continue;
				}
				Schema schema = new Schema();
				schema.setName(rs.getString(1));
				schema.setTables(getTables(schema.getName()));
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
				table.setItsSchema(rs.getString(1));
				table.setColumns(getColumns(catalog, tableNamePattern));
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
	
	public List<List<Object>> getValues(User user, Table table) {
		String tableName = table.getName();
		String schemaName = table.getItsSchema();
		List<List<Object>> lists = new ArrayList<List<Object>>();
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user, "/" + schemaName);
        int sizeOfColumns = getColumns(schemaName, tableName).size();
		try {
				stmt = con.prepareStatement("select * from ?;");
				stmt.setString(1, tableName);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					List<Object> objs = new ArrayList<Object>();
					for (int i=1; i <= sizeOfColumns; i++) {
					objs.add(rs.getObject(i));
//					System.out.print(rs.getString(i) + " ");
					}
					lists.add(objs);
//					System.out.println();
				}
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lists;
	}

	@Override
	public List<List<Object>> getValuesByPage(User user, Table table, int page, int rowsPerPage) {
		String tableName = table.getName();
		String schemaName = table.getItsSchema();
		List<List<Object>> lists = new ArrayList<List<Object>>();
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user, "/" + schemaName);
        List<Column> columns = getColumns(schemaName, tableName);
        int sizeOfColumns = columns.size();
		try {
				stmt = con.prepareStatement("select * from " + tableName + " order by ? desc limit ?,?;");
				stmt.setString(1, columns.get(0).getName());
				stmt.setInt(2, (page-1)*rowsPerPage);
				stmt.setInt(3, rowsPerPage);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					List<Object> objs = new ArrayList<Object>();
					for (int i=1; i <= sizeOfColumns; i++) {
					objs.add(rs.getObject(i));
//					System.out.print(rs.getString(i) + " ");
					}
					lists.add(objs);
//					System.out.println();
				}
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lists;
	}

	@Override
	public boolean isSysSchema(String schemaName) {
		String[] sysSchemas = {"information_schema", "mysql", "performance_schema"};
		for (int i=0; i < sysSchemas.length; i++) {
			if (sysSchemas[i].equals(schemaName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getValuesTotalPages(User user, Table table, int rowsPerPage) {
		int totalRows = 0;
		int totalPages = 0;
		String tableName = table.getName();
		String schemaName = table.getItsSchema();
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user, "/" + schemaName);
		try {
				stmt = con.prepareStatement("select count(*) from " + tableName + ";");
				ResultSet rs = stmt.executeQuery();
				rs.next();
				totalRows = rs.getInt(1);
				con.close();
				if (totalRows % rowsPerPage == 0) {
					totalPages = totalRows / rowsPerPage;
				} else {
					totalPages = totalRows / rowsPerPage + 1;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalPages;
	}

}

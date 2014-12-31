package com.tajpure.dbms.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tajpure.dbms.entity.Column;
import com.tajpure.dbms.entity.Function;
import com.tajpure.dbms.entity.Schema;
import com.tajpure.dbms.entity.StoredProcedure;
import com.tajpure.dbms.entity.Table;
import com.tajpure.dbms.entity.User;
import com.tajpure.dbms.entity.View;
import com.tajpure.dbms.util.Assert;
import com.tajpure.dbms.util.ConnectionPool;

/**
 * 
 * @author tajpure
 *
 */
public class MySQLMetaDataWorker extends DatabaseMetaDataWorker {

	public MySQLMetaDataWorker(Connection con, User user) {
		super(con, user);
	}

	@Override
	public Schema getSchema(String schemaName) {
		Schema schema = null;
		List<Schema> schemas = getSchemas();
		for (Schema tmp : schemas) {
			if (tmp.getName().equals(schemaName)) {
				schema = tmp;
				break;
			}
		}
		return schema;
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
		table = getTables(schemaName, "%", tableName, new String[] {"TABLE"}, true).get(0);
		return table;
	}

	@Override
	public List<Table> getTables(String schemaName) {
		List<Table> tables = null;
		tables = getTables(schemaName, "%", "%", new String[] {"TABLE"}, false);
		return tables;
	}
	
	public List<Table> getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types, boolean needColumns) {
		List<Table> tables = new ArrayList<Table>();
		
		try {
			ResultSet rs = metaData.getTables(catalog, schemaPattern, tableNamePattern, types);
			while (rs.next()) {
				Table table = new Table();
				table.setName(rs.getString("TABLE_NAME"));
				table.setItsSchema(rs.getString("TABLE_CAT"));
				table.setTableType(rs.getString("TABLE_TYPE"));
				table.setRemarks(rs.getString("REMARKS"));
				// For loading the table list faster
				if (needColumns) {
					table.setColumns(getColumns(catalog, tableNamePattern));
				}
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
		if (catalog == null || tableNamePattern == null) return null;
		List<Column> columns = new ArrayList<Column>();
		Map<String,String> primaryKeyMap = new HashMap<String,String>();
		
		try {
			ResultSet rs = metaData.getPrimaryKeys(catalog, schemaPattern, tableNamePattern);
			while (rs.next()) {
				primaryKeyMap.put(rs.getString("COLUMN_NAME"), "exist");
			}
			rs = metaData.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
			while (rs.next()) {
				Column column = new Column();
				column.setName(rs.getString("COLUMN_NAME"));
				column.setDataType(rs.getInt("DATA_TYPE"));
				column.setColumnSize(rs.getInt("COLUMN_SIZE"));
				column.setNN(mapStrToBoo(rs.getString("IS_NULLABLE")));
				column.setAI(mapStrToBoo(rs.getString("IS_AUTOINCREMENT")));
				column.setColumnDefault(rs.getString("REMARKS"));
				if ("exist".equals(primaryKeyMap.get(column.getName()))) {
					column.setPK(true);
				}
				columns.add(column);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return columns;
	}
	
	/**
	 * "YES" -> true, "NO" or others -> false
	 * @param result
	 * @return
	 */
	public boolean mapStrToBoo(String result) {
		if ("YES".equals(result)) {
			return true;
		} else {
			return false;
		}
	}
	
	public List<List<Object>> getValues(Table table) {
		String tableName = table.getName();
		String schemaName = table.getItsSchema();
		List<List<Object>> lists = new ArrayList<List<Object>>();
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user,schemaName);
        int sizeOfColumns = getColumns(schemaName, tableName).size();
        
		try {
				stmt = con.prepareStatement("select * from ?;");
				stmt.setString(1, tableName);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					List<Object> objs = new ArrayList<Object>();
					for (int i=1; i <= sizeOfColumns; i++) {
						objs.add(rs.getObject(i));
					}
					lists.add(objs);
				}
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lists;
	}

	@Override
	public List<List<Object>> getValuesByPage(Table table, int page, int rowsPerPage) {
		String tableName = table.getName();
		String schemaName = table.getItsSchema();
		List<List<Object>> lists = new ArrayList<List<Object>>();
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user,schemaName);
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
					}
					lists.add(objs);
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
	public int getValuesTotalPages(Table table, int rowsPerPage) {
		int totalRows = 0;
		int totalPages = 0;
		String tableName = table.getName();
		String schemaName = table.getItsSchema();
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user,schemaName);
        
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

	@Override
	public <T> int insertValue(Table table, List<T> obj) {
		String tableName = table.getName();
		String schemaName = table.getItsSchema();
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user,schemaName);
        List<Column> columns = getColumns(schemaName, tableName);
        int rs = 0;
        String questionMarks = "";
        
        for (int i=0; i < columns.size()-1; i++) {
        	questionMarks += "?, ";
        }
        
        questionMarks = questionMarks + "?";
        
		try {
				stmt = con.prepareStatement("insert into " + tableName + " values(" + questionMarks + ");");
				for (int i=1; i <= columns.size(); i++) {
					stmt.setObject(i, mapDataType(columns.get(i-1), obj.get(i-1).toString()));
		        }
				rs = stmt.executeUpdate();
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	@Override
	public <T> int insertValues(Table table, List<List<T>> list) {
		String tableName = table.getName();
		String schemaName = table.getItsSchema();
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user,schemaName);
        List<Column> columns = getColumns(schemaName, tableName);
        int rs = 0;
        String questionMarks = "";
        
        for (int i=0; i < columns.size()-1; i++) {
        	questionMarks += "?, ";
        }
        
        questionMarks = questionMarks + "?";
        
		try {
				for (List<T> obj : list) {
					stmt = con.prepareStatement("insert into " + tableName + " values(" + questionMarks + ");");
					for (int i=1; i <= columns.size(); i++) {
						stmt.setObject(i, mapDataType(columns.get(i-1), obj.get(i-1).toString()));
					}
					rs = stmt.executeUpdate();
				}
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * TODO To support all the data type
	 * @param column
	 * @param val
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Object mapDataType(Column column, String val) {
		if (val == null || val.length() == 0) { return null;}
		Object obj = null;
		
		switch (column.getDataType()) {
		case Types.BIGINT : obj = Long.parseLong(val); break;
		case Types.BOOLEAN : obj = Boolean.parseBoolean(val); break;
		case Types.CHAR : obj = val; break;
		case Types.DATE : obj = Date.parse(val); break;
		case Types.DOUBLE : obj = Double.parseDouble(val); break;
		case Types.FLOAT : obj = Float.parseFloat(val); break;
		case Types.INTEGER : obj = Integer.parseInt(val); break;
		case Types.VARCHAR :
		case Types.LONGNVARCHAR : obj = val; break;
		default : Assert.error("This data type isn't supported now.(column -type -size: " + column + ")");
		}
		
		return obj;
	}

	@Override
	public <T> int updateValues(Table table, List<List<T>> oldList, List<List<T>> newList) {
		String tableName = table.getName();
		String schemaName = table.getItsSchema();
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user,schemaName);
        List<Column> columns = getColumns(schemaName, tableName);
        String SQL = getUpdateSQL(columns, tableName);
        int columnsSize = columns.size();
        int rs = 0;
        
		try {
				for (int i = 0 ; i < newList.size(); i++) {
					List<T> oldObj = oldList.get(i);
					List<T> newObj = newList.get(i);
					stmt = con.prepareStatement(SQL);
					for (int j = 1; j <= columnsSize; j++) {
						stmt.setObject(j, mapDataType(columns.get(j-1), newObj.get(j-1).toString()));
					}
					for (int j = 1; j <= columnsSize; j++) {
						stmt.setObject(j + columnsSize, mapDataType(columns.get(j-1), oldObj.get(j-1).toString()));
					}
					rs = stmt.executeUpdate();
				}
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public String getUpdateSQL(List<Column> columns, String tableName) {
		StringBuilder SQL = new StringBuilder("update ").append(tableName).append(" set ");
		int i = 0;
		Column column = null;
		
		for (i = 0; i < columns.size()-1; i++) {
			column = columns.get(i);
			SQL.append(column.getName()).append(" = ?, ");
		}
		
		column = columns.get(i);
		SQL.append(column.getName()).append(" = ?");
		SQL.append(" where ");
		
		for (i = 0; i < columns.size()-1; i++) {
			column = columns.get(i);
			SQL.append(column.getName()).append(" = ? and ");
		}
		
		column = columns.get(i);
		SQL.append(column.getName()).append(" = ?;");
		return SQL.toString();
	}

	@Override
	public <T> int deleteValue(Table table, List<List<T>> list) {
		String tableName = table.getName();
		String schemaName = table.getItsSchema();
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user,schemaName);
        List<Column> columns = getColumns(schemaName, tableName);
        String SQL = getDeleteSQL(columns, tableName);
        int columnsSize = columns.size();
        int rs = 0;
        
		try {
				for (int i = 0 ; i < list.size(); i++) {
					List<T> delObj = list.get(i);
					stmt = con.prepareStatement(SQL);
					for (int j = 1; j <= columnsSize; j++) {
						stmt.setObject(j, mapDataType(columns.get(j-1), delObj.get(j-1).toString()));
					}
					rs = stmt.executeUpdate();
				}
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public String getDeleteSQL(List<Column> columns, String tableName) {
		StringBuilder SQL = new StringBuilder("delete from ").append(tableName).append(" where ");
		int i = 0;
		Column column = null;
		
		for (i = 0; i < columns.size()-1; i++) {
			column = columns.get(i);
			SQL.append(column.getName()).append(" = ? and ");
		}
		
		column = columns.get(i);
		SQL.append(column.getName()).append(" = ?;");
		return SQL.toString();
	}

	@Override
	public List<View> getViews(String schemaName) {
		List<View> views = new ArrayList<View>();
		views = getViews(schemaName, "%", "%", new String[] {"VIEW"}, false);
		return views;
	}
	
	public List<View> getViews(String catalog, String schemaPattern,
			String tableNamePattern, String types[], boolean needColumns) {
		List<View> views = new ArrayList<View>();
		
		try {
			ResultSet rs = metaData.getTables(catalog, schemaPattern, tableNamePattern, types);
			while (rs.next()) {
				View view = new View();
				view.setName(rs.getString("TABLE_NAME"));
				view.setItsSchema(rs.getString("TABLE_CAT"));
				view.setTableType(rs.getString("TABLE_TYPE"));
				view.setRemarks(rs.getString("REMARKS"));
				// For loading the view list faster
				if (needColumns) {
					view.setColumns(getColumns(catalog, tableNamePattern));
				}
				views.add(view);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return views;
	}

	@Override
	public List<StoredProcedure> getStoredProcedures(String schemaName) {
		List<StoredProcedure> procedures = new ArrayList<StoredProcedure>();
		procedures = getStoredProcedures(schemaName, "%", "%");
		return procedures;
	}
	
	public List<StoredProcedure> getStoredProcedures(String catalog, String schemaPattern, String procedureNamePattern) {
		List<StoredProcedure> procedures = new ArrayList<StoredProcedure>();
		
		try {
			ResultSet rs = metaData.getProcedures(catalog, schemaPattern, procedureNamePattern);
			while (rs.next()) {
				StoredProcedure procedure = new StoredProcedure();
				procedure.setName(rs.getString("PROCEDURE_NAME"));
				procedure.setRemarks(rs.getString("REMARKS"));
				procedures.add(procedure);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return procedures;
	}

	@Override
	public List<Function> getFunctions(String schemaName) {
		List<Function> functions = new ArrayList<Function>();
		functions = getFunctions(schemaName, "%", "%");
		return functions;
	}
	
	public List<Function> getFunctions(String catalog,String schemaPattern,String functionNamePattern) {
		List<Function> functions = new ArrayList<Function>();
		
		try {
			ResultSet rs = metaData.getFunctions(catalog, schemaPattern, functionNamePattern);
			while (rs.next()) {
				Function function = new Function();
				function.setName(rs.getString("FUNCTION_NAME"));
				function.setRemarks(rs.getString("REMARKS"));
				functions.add(function);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return functions;
	}
	
	public int createSchema(String schemaName) {
		int rs = 0;
//		rs = metaData.
		return rs;
	}

	@Override
	public int updateColumns(Table table, List<Column> oldColumns,
			List<Column> newColumns) {
		String tableName = table.getName();
		String schemaName = table.getItsSchema();
		String SQL = null;
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user,schemaName);
        int rs = 0;
        
        try {
        	for (int i = 0; i < oldColumns.size(); i++) {
        		Column oldColumn = oldColumns.get(i);
        		Column newColumn = newColumns.get(i);
        		SQL = getUpdateColumnsSQL(tableName, oldColumn, newColumn);
        		stmt = con.prepareStatement(SQL);
        		rs = stmt.executeUpdate();
        	}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return rs;
	}
	
	public String getUpdateColumnsSQL(String tableName, Column oldColumn,
			Column newColumn) {
		StringBuilder SQL = new StringBuilder("alter table ");
		SQL.append(tableName)
			.append(" change ")
			.append(oldColumn.getName())
			.append(" ")
			.append(newColumn.getName())
			.append(" ")
			.append(mapDataTypeToStr(newColumn.getDataType()))
			.append("(")
			.append(newColumn.getColumnSize())
			.append(")");
		
		if (newColumn.isNN()) {
			SQL.append(" not null");
		}
		if (newColumn.isUN()) {
			SQL.append(" unsigned");
		}
		if (newColumn.isZF()) {
			SQL.append(" default 0");
		}
		if (newColumn.isAI()) {
			SQL.append(" auto_increment");
		}
		
		SQL.append(";");
		return SQL.toString();
	}

	@Override
	public int deleteColumns(Table table, List<Column> columns) {
		String tableName = table.getName();
		String schemaName = table.getItsSchema();
		String SQL = null;
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user,schemaName);
        int rs = 0;
        
        try {
        	for (int i = 0; i < columns.size(); i++) {
        		Column column = columns.get(i);
        		SQL = getDeleteSQL(tableName, column);
        		stmt = con.prepareStatement(SQL);
        		rs = stmt.executeUpdate();
        	}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return rs;
	}
	
	public String getDeleteSQL(String tableName, Column column) {
		StringBuilder SQL = new StringBuilder("alter table ");
		SQL.append(tableName)
			.append(" drop  column ")
			.append(column.getName())
			.append(";");
		return SQL.toString();
	}

	@Override
	public int insertColumn(Table table, Column column) {
		String tableName = table.getName();
		String schemaName = table.getItsSchema();
		String SQL = null;
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user,schemaName);
        int rs = 0;
        
        try {
        		SQL = getInsertColumnSQL(tableName, column);
        		stmt = con.prepareStatement(SQL);
        		rs = stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return rs;
	}
	
	public String getInsertColumnSQL(String tableName, Column column) {
		StringBuilder SQL = new StringBuilder("alter table ");
		SQL.append(tableName)
			.append(" add column ")
			.append(column.getName())
			.append(" ")
			.append(mapDataTypeToStr(column.getDataType()))
			.append("(")
			.append(column.getColumnSize())
			.append(")");
		
		if (column.isNN()) {
			SQL.append(" not null");
		}
		if (column.isUN()) {
			SQL.append(" unsigned");
		}
		if (column.isZF()) {
			SQL.append(" default 0");
		}
		if (column.isAI()) {
			SQL.append(" auto_increment");
		}
		
		SQL.append(";");
		return SQL.toString();
	}
	
	/**
	 * TODO To support all the data type
	 * @param dataType
	 * @return
	 */
	public String mapDataTypeToStr(int dataType) {
		String type = null;
		
		switch (dataType) {
		case Types.BIGINT : type = "bigint"; break;
		case Types.BOOLEAN : type = "tinyint"; break;
		case Types.DATE : type = "date"; break;
		case Types.DOUBLE : type = "double"; break;
		case Types.FLOAT : type = "float"; break;
		case Types.INTEGER : type = "int"; break;
		case Types.CHAR : type = "char"; break;
		case Types.VARCHAR : type = "varchar"; break;
		case Types.LONGNVARCHAR : type = "longblob"; break;
		default : Assert.error("This data type isn't supported now.(dataType: " + dataType + ")");
		}
		
		return type;
	}

	@Override
	public int createTable(Table table) {
		String schemaName = table.getItsSchema();
		String SQL = null;
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user,schemaName);
        int rs = 0;
        
        try {
        		SQL = getCreatTableSQL(table);
        		stmt = con.prepareStatement(SQL);
        		rs = stmt.executeUpdate();
        		con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return rs;
	}
	
	public String getCreatTableSQL(Table table) {
		int i = 0;
		StringBuilder SQL = new StringBuilder("create table ");
		SQL.append(table.getName()).append("(");
		List<Column> columns = table.getColumns();
		Column column = null;
		
		for (; i < columns.size()-1; i++) {
			column = columns.get(i);
			SQL.append(column.getName())
				.append(" ")
				.append(mapDataTypeToStr(column.getDataType()))
				.append("(")
				.append(column.getColumnSize())
				.append("),");
		}
		
		column = columns.get(i);
		SQL.append(column.getName())
			.append(" ")	
			.append(mapDataTypeToStr(column.getDataType()))
			.append("(")
			.append(column.getColumnSize())
			.append("));");
		return SQL.toString();
	}

	@Override
	public int dropTable(Table table) {
		String schemaName = table.getItsSchema();
		String SQL = null;
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user,schemaName);
        int rs = 0;
        
        try {
        		SQL = getDropTableSQL(table);
        		stmt = con.prepareStatement(SQL);
        		rs = stmt.executeUpdate();
        		con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return rs;
	}
	
	public String getDropTableSQL(Table table) {
		StringBuilder SQL = new StringBuilder("drop table ");
		SQL.append(table.getName());
		SQL.append(";");
		return SQL.toString();
	}

	@Override
	public int dropTables(List<Table> tables) {
		String schemaName = tables.get(0).getItsSchema();
		String SQL = null;
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user,schemaName);
        int rs = 0;
        
        try {
        		for (Table table : tables) {
		        	SQL = getDropTableSQL(table);
		        	stmt = con.prepareStatement(SQL);
		        	rs = stmt.executeUpdate();
        		}
        		con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return rs;
	}

	@Override
	public int updateTables(List<Table> oldTables, List<Table> newTables) {
		String schemaName = oldTables.get(0).getItsSchema();
		String SQL = null;
        PreparedStatement stmt = null;
        Connection con = ConnectionPool.getNewConnection(user, schemaName);
        int rs = 0;
        
        try {
        		for (int i = 0; i < oldTables.size(); i++) {
        			Table oldTable = oldTables.get(i);
        			Table newTable = newTables.get(i);
        			if (oldTable.getName().equals(newTable.getName())) {
        				continue;
        			}
		        	SQL = getUpdateTablesSQL(oldTable, newTable);
		        	stmt = con.prepareStatement(SQL);
		        	rs = stmt.executeUpdate();
        		}
        		con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return rs;
	}
	
	public String getUpdateTablesSQL(Table oldTable, Table newTable) {
		StringBuilder SQL = new StringBuilder("rename table ");
		SQL.append(oldTable.getName())
			.append(" to ")
			.append(newTable.getName());
		return SQL.toString();
	}

	@Override
	public void executeCommond(String sql) {
		// TODO Auto-generated method stub
		
	}
}

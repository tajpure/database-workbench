package com.tajpure.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tajpure.dbms.entity.Schema;

public class OracleMetaDataWorker extends DatabaseMetaDataWorker {
	
	public OracleMetaDataWorker(Connection con) {
		super(con);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Schema> getSchemas() {
		List<Schema> schemas = new ArrayList<Schema>();
		
		try {
			ResultSet rs = metaData.getSchemas();
			while (rs.next()) {
				Schema schema = new Schema();
				schema.setSchemaName(rs.getString(1));
				schemas.add(schema);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(schemas);
		return schemas;
	}
	
}


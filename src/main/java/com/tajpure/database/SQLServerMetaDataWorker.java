package com.tajpure.database;

import java.sql.Connection;
import java.util.List;

import com.tajpure.dbms.entity.Schema;

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

}

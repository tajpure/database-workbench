package com.tajpure.database;

import com.mysql.jdbc.DatabaseMetaData;

/**
 * 
 * @author taojx
 *
 */
public class MySQLMetaDataWorker extends DatabaseMetaDataWorker {
	
	private DatabaseMetaData dbMetaData = null;

	public DatabaseMetaData getDbMetaData() {
		return dbMetaData;
	}

	public void setDbMetaData(DatabaseMetaData dbMetaData) {
		this.dbMetaData = dbMetaData;
	}

}

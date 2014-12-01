package com.tajpure.database;

import com.mysql.jdbc.DatabaseMetaData;

/**
 * 
 * @author taojx
 *
 */
public class MySQLMetaDataWorker extends DatabaseMetaDataWorker {
	
	private DatabaseMetaData metaData = null;

	public DatabaseMetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(DatabaseMetaData MetaData) {
		this.metaData = MetaData;
	}

}

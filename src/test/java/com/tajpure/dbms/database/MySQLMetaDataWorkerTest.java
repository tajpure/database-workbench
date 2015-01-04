package com.tajpure.dbms.database;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tajpure.dbms.entity.User;

public class MySQLMetaDataWorkerTest {

	DatabaseMetaDataWorker worker = null;
	
	@Before
	public void init() {
		DatabaseMetaDataFactory factory = DatabaseMetaDataFactory.getInstance();
		User user = new User("google", "google", Database.MySQL);
		user.setDriver("com.mysql.jdbc.Driver");;
		user.setUrl("jdbc:mysql://localhost:3306");
		worker = factory.getWorker(user);
	}
	
	@Test
	public void testGetDriverName() {
		assertNotNull(worker.getDriverName());
	}
	
	@Test
	public void testGetSchemas() {
		worker.getSchemas();
	}

	@Test
	public void testGetTable() {
		assertNotNull(worker.getTable("information_schema", "TABLES"));
	}
	
	@Test
	public void testGetTables() {
		assertNotNull(worker.getTables("information_schema"));
	}
	
	@Test
	public void testGetColumn() {
		assertNotNull(worker.getColumns("information_schema", "TABLES"));
	}
	
	@Test
	public void testGetColumns() {
		assertNotNull(worker.getColumn("information_schema", "TABLES", "TABLE_CATALOG"));
	}
	
	@After
	public void drop() {
		worker.drop();
	}
}

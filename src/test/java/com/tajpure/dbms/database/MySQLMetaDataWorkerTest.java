package com.tajpure.dbms.database;

import static org.junit.Assert.*;

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
		worker = factory.getWorker(user);
	}
	
	@Test
	public void testGetDriverName() {
		assertEquals("MySQL Connector Java", worker.getDriverName());
	}
	
	@Test
	public void testGetSchemas() {
		worker.getSchemas();
	}

	@Test
	public void testGetTable() {
		assertNotNull(worker.getTable("hoolai_share", "authorities"));
	}
	
	@Test
	public void testGetTables() {
		assertNotNull(worker.getTables("hoolai_share"));
	}
	
	@Test
	public void testGetColumn() {
		assertNotNull(worker.getColumns("hoolai_share", "user"));
	}
	
	@Test
	public void testGetColumns() {
		assertNotNull(worker.getColumn("hoolai_share", "user", "name"));
	}
	
	@After
	public void drop() {
		worker.drop();
	}
}

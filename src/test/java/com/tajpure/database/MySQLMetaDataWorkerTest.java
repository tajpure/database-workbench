package com.tajpure.database;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tajpure.dbms.database.Database;
import com.tajpure.dbms.database.DatabaseMetaDataFactory;
import com.tajpure.dbms.database.DatabaseMetaDataWorker;

public class MySQLMetaDataWorkerTest {
	
	DatabaseMetaDataWorker worker = null;
	
	@Before
	public void init() {
		DatabaseMetaDataFactory factory = DatabaseMetaDataFactory.getInstance();
		worker = factory.getWorker(Database.MySQL);
	}
	
	@Test
	public void testGetDriverName() {
		assertEquals("MySQL Connector Java", worker.getDriverName());
	}
	
	@Test
	public void testGetSchemas() {
		worker.getSchemas();
	}
	
	@After
	public void drop() {
		worker.drop();
	}
}

package com.tajpure.database;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tajpure.dbms.database.Database;
import com.tajpure.dbms.database.DatabaseMetaDataFactory;
import com.tajpure.dbms.database.DatabaseMetaDataWorker;
import com.tajpure.dbms.entity.Table;
import com.tajpure.dbms.entity.User;

public class MySQLMetaDataWorkerTest {
	
	DatabaseMetaDataWorker worker = null;
	
	User user = null;
	
	@Before
	public void init() {
		DatabaseMetaDataFactory factory = DatabaseMetaDataFactory.getInstance();
		user = new User("root", "930710", Database.MySQL);
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
	public void testGetTables() {
		worker.getTables("");
	}
	
	@Test
	public void testGetValues() {
		Table table = new Table("hoolai_share", "user");
		worker.getValues(user, table);
	}
	
	@After
	public void drop() {
		worker.drop();
	}
}

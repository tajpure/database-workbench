package com.tajpure.dbms.util;

import org.junit.Before;
import org.junit.Test;

import com.tajpure.dbms.database.Database;
import com.tajpure.dbms.entity.User;

public class ValueInjectorTest {
	
	@Before
	public void init() {
		
	}

	@Test
	public void testInject() {
		User user = new User("taojx", "taojx", Database.MySQL);
		ValueInjector.inject(null, user);
	}
}

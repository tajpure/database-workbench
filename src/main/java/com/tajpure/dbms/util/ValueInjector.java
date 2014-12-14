package com.tajpure.dbms.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.List;

/**
 * Inject the value from ResultSet into the entity.
 * @author taojx
 *
 */
public class ValueInjector {
	
	public static void inject(ResultSet rs, Object obj) {
		if (obj == null) return;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
        	System.out.println(fields[i].getName());
        }
	}
	
	public static void injectList(ResultSet rs, List<Object> objs) {
		
	}
	
}

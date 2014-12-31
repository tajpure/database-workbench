package com.tajpure.dbms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLUtil {
	
	public static boolean isSeletedSQL(String SQL) {
		SQL = SQL.toLowerCase();
		Pattern pat = Pattern.compile("^\\s*");
		Matcher mat = pat.matcher(SQL);
		SQL = mat.replaceAll("");
		if (SQL.indexOf("select") == 0) {
			return true;
		} else {
			return false;
		}
	}
}

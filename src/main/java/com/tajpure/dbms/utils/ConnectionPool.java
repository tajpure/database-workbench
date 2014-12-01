package com.tajpure.dbms.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;

public class ConnectionPool {

	private static LinkedList<Connection> IdleConnection = new LinkedList<Connection>();
 
	private static HashSet<Connection> UsedConnection = new HashSet<Connection>();

	private static String url;

	private static String driver;

	private static String username;

	private static String password;

	private static int maxConnect = 2;

	static final boolean DEBUG = false;

	static private long lastClearClosedConnection = System.currentTimeMillis();

	public static long CHECK_CLOSED_CONNECTION_TIME = 5000;

	static {
		Properties props = new Properties();
		try {
			props.load(ConnectionPool.class.getClassLoader()
					.getResourceAsStream("mysql.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (props != null) {
			url = props.getProperty("url");
			driver = props.getProperty("driver");
			username = props.getProperty("username");
			password = props.getProperty("password");

			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static synchronized Connection getConnection() {
		clearClosedConnection();

		// Print the total numbers of current connections
		if (DEBUG) {
			System.out.println("Current used connections number:" + getUsedConnectionCount());
			System.out.println("Current idle connections number:" + getIdleConnectionCount());
			System.out.println("The total numbers of current connections:" + getConnectionCount());
		}

		// Seek for idle connections
		while (IdleConnection.size() > 0) {
			try {
				Connection con = (Connection) IdleConnection.removeFirst();

				if (con.isClosed()) {
					continue;
				}

				UsedConnection.add(con);
				if (DEBUG) {
					System.out.println("Initialize the connection.");
				}
				return con;
			} catch (SQLException e) {
			}
		}

		int newCount = getIncreasingConnectionCount();
		LinkedList<Connection> list = new LinkedList<Connection>();
		Connection con = null;

		for (int i = 0; i < newCount; i++) {
			con = getNewConnection();
			if (con != null) {
				list.add(con);
			}
		}

		if (list.size() == 0)
			return null;

		con = (Connection) list.removeFirst();
		UsedConnection.add(con);
		IdleConnection.addAll(list);
		list.clear();

		return con;
	}

	public static Connection getNewConnection() {
		try {
			Connection con = DriverManager.getConnection(url, username,
					password);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static synchronized void pushConnectionBackToPool(Connection con) {
		boolean exist = UsedConnection.remove(con);
		if (exist) {
			IdleConnection.addLast(con);
			if (DEBUG){
				System.out.println("This connection has been pushed into the connection pool.");
			}
		}
	}

	public static int close() {
		int count = 0;

		Iterator<Connection> iterator = IdleConnection.iterator();
		while (iterator.hasNext()) {
			try {
				((Connection) iterator.next()).close();
				count++;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		IdleConnection.clear();

		iterator = UsedConnection.iterator();
		while (iterator.hasNext()) {
			try {
				((Connection) iterator.next()).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		UsedConnection.clear();

		return count;
	}

	private static void clearClosedConnection() {
		long time = System.currentTimeMillis();

		if (time < lastClearClosedConnection) {
			time = lastClearClosedConnection;
			return;
		}

		if (time - lastClearClosedConnection < CHECK_CLOSED_CONNECTION_TIME) {
			return;
		}

		lastClearClosedConnection = time;

		Iterator<Connection> iterator = IdleConnection.iterator();
		while (iterator.hasNext()) {
			Connection con = (Connection) iterator.next();

			try {
				if (con.isClosed()) {
					iterator.remove();
				}
			} catch (SQLException e) {
				iterator.remove();

				if (DEBUG) {
					System.out.println("The bad connection has been broken.");
				}
			}
		}

		int decrease = getDecreasingConnectionCount();

		while (decrease > 0 && IdleConnection.size() > 0) {
			Connection con = (Connection) IdleConnection.removeFirst();

			try {
				con.close();
			} catch (SQLException e) {

			}

			decrease--;
		}
	}

	public static int getIncreasingConnectionCount() {
		int count = 1;
		count = getConnectionCount() / 4;

		if (count < 1)
			count = 1;

		return count;
	}

	public static int getDecreasingConnectionCount() {
		int count = 0;

		if (getConnectionCount() > maxConnect) {
			count = getConnectionCount() - maxConnect;
		}

		return count;
	}

	public static synchronized int getIdleConnectionCount() {
		return IdleConnection.size();
	}

	public static synchronized int getUsedConnectionCount() {
		return UsedConnection.size();
	}

	public static synchronized int getConnectionCount() {
		return IdleConnection.size() + UsedConnection.size();
	}

	public static void main(String[] args) {
		Connection conn = getConnection();
		System.out.println(conn);
	}
}

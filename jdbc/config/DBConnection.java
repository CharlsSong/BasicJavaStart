package com.biz.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection dbConn = null;
	
	static {
		try {
			Class.forName(DBContract.DB_CONN.JDBC_DRIVER);
			dbConn = DriverManager.getConnection(DBContract.DB_CONN.URL,DBContract.DB_CONN.USER_NAME,DBContract.DB_CONN.PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getDBConnection() {
		return dbConn;
	}
}

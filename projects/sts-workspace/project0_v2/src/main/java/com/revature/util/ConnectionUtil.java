package com.revature.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class ConnectionUtil {													// CLASS for establishing a connection to DB
	
	private static Connection conn;												// VARIABLE for storing connection to DB 
	
	public static Connection getConnectionFromEnv() throws SQLException {		// METHOD for establishing a connection to DB
		String url = System.getenv("DB_URL");									// fetching url/username/passsword info from env
		String username = System.getenv("DB_USER");
		String password = System.getenv("DB_PASS");
		
		if (conn == null || conn.isClosed()) {									// checks to see if there is an active connection or not
			conn = DriverManager.getConnection(url, username, password);		// created a connection to DB if no connection already established 
		} return conn;															// returns the connection
	}
}
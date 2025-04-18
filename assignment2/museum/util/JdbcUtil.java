package com.museum.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
	public static Connection conn = null;

	public static Connection getConnection() throws SQLException {

		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac", "root", "password");

		return conn;
	}
}

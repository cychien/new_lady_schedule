package com.lady.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection2 {
	public static void createDatabase(String dbName){
		Connection con = getConnection(dbName);
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("create database " + dbName);
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection createConnection() {
		String str = "";
		return getConnection(str);
	}
	
	
	private static Connection getConnection(String dbName){
		Connection con = null;
		String str = null;
		String connectedDBName = "lady";
		if(dbName.equals("")) {
			str = "jdbc:mysql://localhost:3306/" + connectedDBName;
		}
		else {
			str = "jdbc:mysql://localhost:3306";
		}
		String userName = "root";
		String password = "shift30lady";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(str, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}

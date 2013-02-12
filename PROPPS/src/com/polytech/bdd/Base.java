package com.polytech.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Base {
	private static String urlMySQL;
	private static String user;
	private static String password;
	
	private Connection connection;
	
	public static void initBase(String url,String usr, String pass) throws ClassNotFoundException {
		urlMySQL = url;
		user = usr;
		password = pass;
		Class.forName( "com.mysql.jdbc.Driver" );
	}
	
	public void connect() throws SQLException {
		connection = DriverManager.getConnection( urlMySQL, user,password); 
	}
	
	public void close() throws SQLException {
		if(connection == null) {
			connection.close();
		}
	}
}

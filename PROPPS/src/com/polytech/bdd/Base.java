package com.polytech.bdd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Base {
	private static String urlMySQL;
	private static String user;
	private static String password;
	
	private Connection connection;
	private CallableStatement call;
	
	
	public static void initBase(String url,String usr, String pass) throws ClassNotFoundException {
		urlMySQL = url;
		user = usr;
		password = pass;
		Class.forName( "com.mysql.jdbc.Driver" );
	}
	
	public void connect() throws SQLException {
		connection = DriverManager.getConnection( urlMySQL, user,password); 
	}
	
	public void procedureInit(String name,int nbArg) throws SQLException {
		String sql = "{call " + name + "(";
		for(int i = 0 ; i < nbArg - 1 ; i++) {
			sql += "?,";
		}
		sql += "?)}";
		call = connection.prepareCall(sql);
	}
	
	/**
	 * Methode permettant
	 * 
	 * @param key le nom du paramètre
	 * @param value la valeur du paramètre
	 * @throws SQLException
	 */
	public void setParamString(String key, String value) throws SQLException {
		call.setString(key, value);
	}
	
	public void setParamInt(String key, int value) throws SQLException {
		call.setInt(key, value);
	}
	
	public void setParamBool(String key, boolean value) throws SQLException {
		call.setBoolean(key, value);
	}
	
	public void setParamDate(String key, Date date) throws SQLException {
		call.setDate(key, date);
	}
	
	public ResultSet executeQuery() throws SQLException {
		return call.executeQuery();
	}
	
	public boolean execute() throws SQLException {
		return call.execute();
	}
	
	public ResultSet executeSQLQuery(String sql) throws SQLException {
		Statement st = connection.createStatement();
		st.execute(sql);
		ResultSet result = st.getResultSet();
		st.close();
		return result;
	}
	
	public void executeSQL(String sql) throws SQLException {
		Statement st = connection.createStatement();
		st.execute(sql);
		st.close();
	}
	
	public void close() throws SQLException {
		if(connection != null) {
			connection.close();
		}
	}
}

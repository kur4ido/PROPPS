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
	
	/**
	 * Méthode de connection
	 * 
	 * @throws SQLException
	 */
	public void connect() throws SQLException {
		connection = DriverManager.getConnection( urlMySQL, user,password); 
	}
	
	/**
	 * Méthode d'initialisation de l'appel à une procédure stockée.
	 * 
	 * @param name le nom de la procédure
	 * @param nbArg le nombre d'arguments de la procédure
	 * @throws SQLException
	 */
	public void procedureInit(String name,int nbArg) throws SQLException {
		String sql = "{call " + name + "(";
		for(int i = 0 ; i < nbArg - 1 ; i++) {
			sql += "?,";
		}
		sql += "?)}";
		call = connection.prepareCall(sql);
	}
	
	/**
	 * Methode permettant de modifier un paramètre de type String pour la 
	 * procédure initialisée
	 * 
	 * @param key le nom du paramètre
	 * @param value la valeur du paramètre
	 * @throws SQLException
	 */
	public void setParamString(String key, String value) throws SQLException {
		call.setString(key, value);
	}
	
	/**
	 * Methode permettant de modifier un paramètre de type int pour la 
	 * procédure initialisée
	 * 
	 * @param key le nom du paramètre
	 * @param value la valeur du paramètre
	 * @throws SQLException
	 */
	public void setParamInt(String key, int value) throws SQLException {
		call.setInt(key, value);
	}
	
	/**
	 * Methode permettant de modifier un paramètre de type Boolean pour la 
	 * procédure initialisée
	 * 
	 * @param key le nom du paramètre
	 * @param value la valeur du paramètre
	 * @throws SQLException
	 */
	public void setParamBool(String key, boolean value) throws SQLException {
		call.setBoolean(key, value);
	}
	
	/**
	 * Methode permettant de modifier un paramètre de type Date pour la 
	 * procédure initialisée
	 * 
	 * @param key le nom du paramètre
	 * @param value la valeur du paramètre
	 * @throws SQLException
	 */
	public void setParamDate(String key, Date date) throws SQLException {
		call.setDate(key, date);
	}
	
	/**
	 * Methode permettand d'exécuter une requête SQL et de récupérer son
	 * résultat sous la forme d'un resultSet
	 * 
	 * @return le résultat de la procédure appelée
	 * @throws SQLException
	 */
	public ResultSet executeQuery() throws SQLException {
		return call.executeQuery();
	}
	
	/**
	 * Methode permettant d'exécuter une requête SQL sans récupérer
	 * son résultat
	 * 
	 * @throws SQLException
	 */
	public boolean execute() throws SQLException {
		return call.execute();
	}
	
	/**
	 * Methode permettant d'exécuter une requête SQL en récupérant son résutat;
	 * 
	 * @param sql la requête à exécuter
	 * @return le résultat de la requête
	 * @throws SQLException
	 */
	public ResultSet executeSQLQuery(String sql) throws SQLException {
		Statement st = connection.createStatement();
		st.execute(sql);
		ResultSet result = st.getResultSet();
		st.close();
		return result;
	}
	
	/**
	 * Methode permettant d'exécuter une requête SQL sans récupérer
	 * son résultat
	 * 
	 * @param sql la requête à exécuter
	 * @throws SQLException
	 */
	public void executeSQL(String sql) throws SQLException {
		Statement st = connection.createStatement();
		st.execute(sql);
		st.close();
	}
	
	/**
	 * Methode utilisée pour fermer la connection
	 * 
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		if(connection != null) {
			connection.close();
		}
		call = null;
	}
}

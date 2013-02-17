package com.polytech.propps.bdd;

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
	
	
	/**
	 * Methode de connection
	 * 
	 * @throws SQLException
	 */
	public void connect() throws SQLException {
		connection = DriverManager.getConnection( urlMySQL, user,password); 
	}
	
	/**
	 * Methode d'initialisation de l'appel a une procedure stockee.
	 * 
	 * @param name le nom de la procedure
	 * @param nbArg le nombre d'arguments de la procedure
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
	 * Methode permettant de modifier un parametre de type String pour la 
	 * procedure initialisee
	 * 
	 * @param key le nom du parametre
	 * @param value la valeur du parametre
	 * @throws SQLException
	 */
	public void setParamString(String key, String value) throws SQLException {
		call.setString(key, value);
	}
	
	/**
	 * Methode permettant de modifier un parametre de type int pour la 
	 * procedure initialisee
	 * 
	 * @param key le nom du parametre
	 * @param value la valeur du parametre
	 * @throws SQLException
	 */
	public void setParamInt(String key, Integer value) throws SQLException {
		if(value != null) {
			call.setInt(key, value);
		} else {
			call.setNull(key,java.sql.Types.INTEGER);
		}
	}
	
	/**
	 * Methode permettant de modifier un parametre de type Boolean pour la 
	 * procedure initialisee
	 * 
	 * @param key le nom du parametre
	 * @param value la valeur du parametre
	 * @throws SQLException
	 */
	public void setParamBool(String key, Boolean value) throws SQLException {
		if(value != null) {
			call.setBoolean(key, value);
		}else {
			call.setNull(key,java.sql.Types.BOOLEAN);
		}
	}
	
	/**
	 * Methode permettant de modifier un parametre de type Date pour la 
	 * procedure initialisee
	 * 
	 * @param key le nom du parametre
	 * @param value la valeur du parametre
	 * @throws SQLException
	 */
	public void setParamDate(String key, Date date) throws SQLException {
		call.setDate(key, date);
	}
	
	/**
	 * Methode permettand d'executer une requete SQL et de recuperer son
	 * resultat sous la forme d'un resultSet
	 * 
	 * @return le resultat de la procedure appelee
	 * @throws SQLException
	 */
	public ResultSet executeQuery() throws SQLException {
		return call.executeQuery();
	}
	
	/**
	 * Methode permettant d'executer une requete SQL sans recuperer
	 * son resultat
	 * 
	 * @throws SQLException
	 */
	public boolean execute() throws SQLException {
		return call.execute();
	}
	
	/**
	 * Methode permettant d'executer une requete SQL en recuperant son resutat;
	 * 
	 * @param sql la requete a executer
	 * @return le resultat de la requete
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
	 * Methode permettant d'executer une requete SQL sans recuperer
	 * son resultat
	 * 
	 * @param sql la requete a executer
	 * @throws SQLException
	 */
	public void executeSQL(String sql) throws SQLException {
		Statement st = connection.createStatement();
		st.execute(sql);
		st.close();
	}
	
	/**
	 * Methode utilisee pour fermer la connection
	 * 
	 */
	public void close() {
		if(connection != null) {
			try {
				connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		call = null;
	}
	
/*------------------------------------------
 * Methodes statiques d'initialisation
 -----------------------------------------*/
	public static void setURL(String url) {
		urlMySQL = url;
	}
	
	public static void initBase() throws ClassNotFoundException {
		Class.forName( "com.mysql.jdbc.Driver" );
	}
	
	public static void initBase(String url,String usr, String pass) throws ClassNotFoundException {
		urlMySQL = url;
		user = usr;
		password = pass;
		Class.forName( "com.mysql.jdbc.Driver" );
	}

	public static void setUser(String usr) {
		user = usr;
	}

	public static void setPassWord(String pass) {
		password = pass;	
	}
}

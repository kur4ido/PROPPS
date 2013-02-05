package com.polytech.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.Statement;

public class TestJDBC {
	/* La liste qui contiendra tous les résultats de nos essais */ 
	private List<String> messages = new ArrayList<String>();

	public List<String> executerTests( HttpServletRequest request ) {
		/* Ici, nous placerons le code de nos manipulations */
		try {
			messages.add( "Chargement du driver..." );
			Class.forName( "com.mysql.jdbc.Driver" );
			messages.add( "Driver chargé !" );
		} catch ( ClassNotFoundException e ) { 
			e.printStackTrace();
			messages.add( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"+ e.getMessage() );
		}
		/* Connexion à la base de données */
		String url = "jdbc:mysql://localhost:8888/PROPPS_DB";
		String utilisateur = "root"; 
		String motDePasse = "root"; 
		Connection connexion = null;
		Statement statement = null; 
		ResultSet resultat = null; 
		try {
			messages.add( "Connexion à la base de données..." );
			System.out.println("Connexion à la base de données...");
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
			messages.add( "Connexion réussie !" );
			System.out.println("Connexion réussie !");
		} catch ( SQLException e ) {
			messages.add( "Erreur lors de la connexion : <br/>" + e.getMessage() );
		} finally {
			messages.add( "Fermeture de l'objet ResultSet." ); 
			if ( resultat != null ) {
				try { 
					resultat.close();
				} catch ( SQLException ignore ) { 
					
				}
			}
			messages.add( "Fermeture de l'objet Statement." ); 
			if ( statement != null ) {
				try { 
					statement.close();
				} catch ( SQLException ignore ) { 
					
				}
			} 
			messages.add( "Fermeture de l'objet Connection." );
			if ( connexion != null ) {
				try { 
					connexion.close();
				} catch ( SQLException ignore ) {
					
				}
			}
		} 
		return messages;
	}
}

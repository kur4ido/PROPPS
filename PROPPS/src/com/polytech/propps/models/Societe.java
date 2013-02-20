package com.polytech.propps.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.polytech.propps.bdd.Base;

public class Societe  {
	protected static HashMap<Integer,Societe> listOfSociete;
	public final static String colID = "ID_Societe";
	public final static String colNom = "sNom";
	
	protected int ID_Societe;
	protected String sNom;
	
	
	/**
	 * Constructeur public ne prennant pour paramètre que l'identifiant de la societé
	 * dans la base
	 * Les données (ici le nom uniquement) sont remplies à partir de la map statique.
	 * 
	 * @param ID : l'identifiant de la société dans la BDD
	 */
	public Societe(int ID) {
		ID_Societe = ID;
		if(listOfSociete.containsKey(ID)) {
			sNom = listOfSociete.get(ID).getNom();
		}
	}
	
	/**
	 * Constructeur privé remplissant tous les champs. Ce constructeur doit uniquement être appelé par 
	 * la méthode de remplissage de la map statique (fillList)
	 * 
	 * @param ID : l'Identifiant de la société dans la BDD
	 * @param sNom : le nom de la société
	 */
	private Societe(int ID, String sNom) {
		this.sNom = sNom;
		ID_Societe = ID;
	}
	
	
	/**
	 * Méthode statique devant être appelée à l'initialisation. Elle remplie la map
	 * globale contenant l'ensemble des sociétés répertoriées dans la BDD.
	 */
	public static void fillList() {
		Base b = new Base();
		listOfSociete = new  HashMap<Integer, Societe>();
		try {
			b.connect();
			b.procedureInit("Societe_getAll", 0);
			ResultSet result = b.executeQuery();
			while(result.next()) {
				int ID = result.getInt(colID);
				listOfSociete.put(ID, new Societe(ID,result.getString(colNom)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
	}
	
	/**
	 * Méthode statique permettant de créer une nouvelle société. Les données passées en
	 * paramètre sont insérées dans la BDD et le nouvel objet est inséré dans la map Statique.
	 * Ce même objet est ensuite retourné.
	 * 
	 * @param nomSociete : le nom de la nouvelle société
	 * @return l'objet correspondant à la nouvelle société.
	 */
	public static Societe addSociete(String nomSociete) {
		Base b = new Base();
		Societe s = null;
		try {
			b.connect();
			b.procedureInit("Societe_insertOrUpdate", 1);
			b.setParamString(colNom, nomSociete);
			ResultSet result = b.executeQuery();
			if(result.next()) {
				int ID = result.getInt(colID);
				listOfSociete.put(ID, new Societe(ID,result.getString(colNom)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
		return null;
	}
	
	public String getNom() {
		return sNom;
	}
	
	public int getID() {
		return ID_Societe;
	}
	
}

package com.polytech.propps.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.polytech.propps.bdd.Base;

public class Societe  {
	protected static HashMap<Integer,Societe> listOfSociete;
	public final static String colID = "ID_Societe";
	public final static String colNom = "sNom";
	
	protected int ID_Societe;
	protected String sNom;
	
	
	
	public Societe(int ID) {
		ID_Societe = ID;
		if(listOfSociete.containsKey(ID)) {
			sNom = listOfSociete.get(ID).getNom();
		}
	}
	
	
	private Societe(int ID, String sNom) {
		this.sNom = sNom;
		ID_Societe = ID;
	}
	
	
	
	public static void fillList() {
		Base b = new Base();
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
	
	public static Societe addSociete(String nomSociete) {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Societe_insertOrUpdate", 0);
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
		return null;
	}
	
	public String getNom() {
		return sNom;
	}
	
	public int getID() {
		return ID_Societe;
	}
	
}

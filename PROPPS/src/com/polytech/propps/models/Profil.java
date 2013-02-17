package com.polytech.propps.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.polytech.propps.bdd.Base;

public class Profil {
	protected static HashMap<Integer,Profil> listOfProfil;
	private final static String colID = "ID_Profil";
	private final static String colNom = "sProfil";
	
	protected int ID_Profil;
	protected String sNom;
	
	
	public Profil(int ID) {
		ID_Profil = ID;
		if(listOfProfil.containsKey(ID)) {
			sNom = listOfProfil.get(ID).getNom();
		}
	}
	
	private Profil(int ID, String sProfil) {
		ID_Profil = ID;
		sNom = sProfil;
	}
	
	protected static void fillList() {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Profil_getAll", 0);
			ResultSet result = b.executeQuery();
			while(result.next()) {
				int ID = result.getInt(colID);
				listOfProfil.put(ID, new Profil(ID,result.getString(colNom)));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			b.close();
		}
	}
	
	public String getNom() {
		return sNom;
	}

	public int getID() {
		return ID_Profil;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o != null && o instanceof Profil) {
			Profil p = (Profil) o;
			return ID_Profil == p.ID_Profil;
		}
		return false;
	}
}

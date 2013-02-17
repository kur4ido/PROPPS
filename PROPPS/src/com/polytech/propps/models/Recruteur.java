package com.polytech.propps.models;

import java.sql.SQLException;

import com.polytech.propps.bdd.Base;

public class Recruteur extends Utilisateur {
	
	private final static String colID = "ID_Utilisateur";
	private final static String colSociete = "ID_Societe";
	
	protected int ID_Societe;

	public Recruteur(int ID) {
		super(ID);
	}

	@Override
	public void insertOrUpdate() {
		Base b = new Base();
		try {
			super.insertOrUpdate();
			b.connect();
			b.procedureInit("Recruteur_insertOrUpdate", 2);
			b.setParamInt("_" + colID, super.ID_Utilisateur);
			b.setParamInt("_" + colSociete, ID_Societe);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			b.close();
		}
	}

	@Override
	public void delete() {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Recruteur_delete", 1);
			b.setParamInt("_" + colID, super.ID_Utilisateur);
			super.delete();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			b.close();
		}
		
	} 

	public int getID_Societe() {
		return ID_Societe;
	}

	public void setID_Societe(int iD_Societe) {
		ID_Societe = iD_Societe;
	}

	public static String getColid() {
		return colID;
	}

	public static String getColsociete() {
		return colSociete;
	}

}

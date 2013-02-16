package com.polytech.propps.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.polytech.propps.bdd.Base;

public abstract class Utilisateur implements IModel {
	private final static String colID = "ID_Utilisateur";
	private final static String colNom = "sNom";
	private final static String colPrenom = "sPrenom";
	private final static String colEmail = "sEmail";
	private final static String colPassword = "sPassword";
	
	protected int ID_Utilisateur;
	protected String sNom, sPrenom,sEmail,sPassword;
	protected Adresse adresse;

	public Utilisateur(int ID) {
		ID_Utilisateur = ID;
	}
	
	
	public void insertOrUpdate() {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Utilisateur_insertOrUpdate", 5);
			b.setParamInt("_" + colID, ID_Utilisateur);
			b.setParamString("_" + colNom, sNom);
			b.setParamString("_" + colPrenom, sPrenom);
			b.setParamString("_" + colEmail, sEmail);
			b.setParamString("_" + colPassword, sPassword);
			ResultSet result = b.executeQuery();
			if(ID_Utilisateur < 0 && result.next()) {
				ID_Utilisateur = result.getInt(colID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
	}
	
	public void updatePassWord() {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Utilisateur_updatePassword", 2);
			b.setParamInt("_" + colID, ID_Utilisateur);
			b.setParamString("_" + colPassword, sPassword);
			ResultSet result = b.executeQuery();
			if(result.next()) {
				sPassword = result.getString(colPassword);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
	}
	
	public void delete() {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Utilisateur_delete", 0);
			b.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
	}
}

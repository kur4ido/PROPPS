package com.polytech.propps.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.polytech.propps.bdd.Base;

public abstract class Utilisateur implements IModel {
	protected final static String colID = "ID_Utilisateur";
	protected final static String colNom = "sNom";
	protected final static String colPrenom = "sPrenom";
	protected final static String colEmail = "sEmail";
	protected final static String colPassword = "sPassword";
	
	protected int ID_Utilisateur;
	protected String sNom, sPrenom,sEmail,sPassword;
	protected Adresse adresse;
	private boolean bFill;

	public Utilisateur(int ID) {
		ID_Utilisateur = ID;
		bFill = false;
	}
	
	public Utilisateur(String sNom,String sPrenom,String sEmail,String sPassword) {
		ID_Utilisateur = -1;
		this.sNom = sNom;
		this.sPrenom = sPrenom;
		this.sEmail = sEmail;
		this.sPassword = sPassword;
		bFill = true;
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

	public int getID_Utilisateur() {
		return ID_Utilisateur;
	}

	public void setID_Utilisateur(int iD_Utilisateur) {
		ID_Utilisateur = iD_Utilisateur;
	}

	public String getsNom() {
		return sNom;
	}

	public void setsNom(String sNom) {
		this.sNom = sNom;
	}

	public String getsPrenom() {
		return sPrenom;
	}

	public void setsPrenom(String sPrenom) {
		this.sPrenom = sPrenom;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsPassword() {
		return sPassword;
	}

	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public boolean isbFill() {
		return bFill;
	}

	public void setbFill(boolean bFill) {
		this.bFill = bFill;
	}

	public static String getColid() {
		return colID;
	}

	public static String getColnom() {
		return colNom;
	}

	public static String getColprenom() {
		return colPrenom;
	}

	public static String getColemail() {
		return colEmail;
	}

	public static String getColpassword() {
		return colPassword;
	}
}

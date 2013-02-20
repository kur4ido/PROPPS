package com.polytech.propps.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	protected ArrayList<Message> lstMessage;
	protected boolean bFill;

	public Utilisateur(int ID) {
		ID_Utilisateur = ID;
		adresse = new Adresse(null,null,null,null);
		sNom = null;
		sPrenom = null;
		sEmail = null;
		sPassword = null;
		bFill = false;
		lstMessage = new ArrayList<Message>();
	}
	
	public Utilisateur(String sNom,String sPrenom,String sEmail,String sPassword) {
		ID_Utilisateur = -1;
		this.sNom = sNom;
		this.sPrenom = sPrenom;
		this.sEmail = sEmail;
		this.sPassword = sPassword;
		adresse = new Adresse(null,null,null,null);
		bFill = true;
		lstMessage = new ArrayList<Message>();
	}
	
	public void insertOrUpdate() {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Utilisateur_insertOrUpdate", 9);
			b.setParamInt("_" + colID, ID_Utilisateur);
			b.setParamString("_" + colNom, sNom);
			b.setParamString("_" + colPrenom, sPrenom);
			b.setParamString("_" + colEmail, sEmail);
			b.setParamString("_" + colPassword, sPassword);
			b.setParamString("_" + Adresse.colVille, adresse.getVille());
			b.setParamString("_" + Adresse.colCP, adresse.getCodePostal());
			b.setParamString("_" + Adresse.colAdresse, adresse.getAdresse());
			b.setParamString("_" + Adresse.colPays, adresse.getPays());
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
			b.procedureInit("Utilisateur_delete", 1);
			b.setParamInt("_" + colID, ID_Utilisateur);
			b.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
	}

	public void fillMessages() {
		Base b = new Base();
		try {
			lstMessage = new ArrayList<Message>();
			b.connect();
			b.procedureInit("Utilisateur_getMessages", 1);
			b.setParamInt("_" + colID, ID_Utilisateur);
			ResultSet result = b.executeQuery();
			while(result.next()) {
				Message m = new Message(result.getInt(Message.colID), result.getString(Message.colMessage),
						ID_Utilisateur, result.getDate(Message.colDtMessage));
				lstMessage.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			b.close();
		}
	}
	
	public abstract void fill();
	
	public int getID_Utilisateur() {
		return ID_Utilisateur;
	}

	public void setID_Utilisateur(int iD_Utilisateur) {
		ID_Utilisateur = iD_Utilisateur;
	}

	public String getsNom() {
		if(!bFill) {
			fill();
		}
		return sNom;
	}

	public void setsNom(String Nom) {
		this.sNom = Nom;
		bFill = true;
	}

	public String getsPrenom() {
		if(!bFill) {
			fill();
		}
		return sPrenom;
	}

	public void setsPrenom(String Prenom) {
		this.sPrenom = Prenom;
		bFill = true;
	}

	public String getsEmail() {
		if(!bFill) {
			fill();
		}
		return sEmail;
	}

	public void setsEmail(String Email) {
		this.sEmail = Email;
		bFill = true;
	}

	public String getsPassword() {
		if(!bFill) {
			fill();
		}
		return sPassword;
	}

	public void setPassword(String Password) {
		this.sPassword = Password;
		bFill = true;
	}

	public Adresse getAdresse() {
		if(!bFill) {
			fill();
		}
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
		bFill = true;
	}

	public boolean isbFill() {
		return bFill;
	}

	public void setbFill(boolean bFill) {
		this.bFill = bFill;
	}

}

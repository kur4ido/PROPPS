package com.polytech.propps.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.polytech.propps.bdd.Base;

public class ExperiencePro {
	public static String colID = "ID_ExperiencePro";
	public static String colNbExpertise = "NbExpertise";
	
	protected String sPosteOccupe;
	protected String sDescription;
	protected String sDirection;
	protected Date dtDebut;
	protected Date dtFin;
	protected Profil profil;
	protected ArrayList<Expertise> lstExpertise;
	protected Societe societe;
	protected int ID_ExpPro;
	
	private boolean bFill;
	
	public ExperiencePro(int ID) {
		bFill = false;
		this.ID_ExpPro = ID;
		lstExpertise = new ArrayList<Expertise>();
	}
	
	
	public String getsPosteOccupe() {
		return sPosteOccupe;
	}


	public void setsPosteOccupe(String sPosteOccupe) {
		this.sPosteOccupe = sPosteOccupe;
	}


	public String getsDescription() {
		return sDescription;
	}


	public void setsDescription(String sDescription) {
		this.sDescription = sDescription;
	}


	public String getsDirection() {
		return sDirection;
	}


	public void setsDirection(String sDirection) {
		this.sDirection = sDirection;
	}


	public Date getDtDebut() {
		return dtDebut;
	}


	public void setDtDebut(Date dtDebut) {
		this.dtDebut = dtDebut;
	}


	public Date getDtFin() {
		return dtFin;
	}


	public void setDtFin(Date dtFin) {
		this.dtFin = dtFin;
	}


	public Profil getProfil() {
		return profil;
	}


	public void setProfil(Profil profil) {
		this.profil = profil;
	}


	public Societe getSociete() {
		return societe;
	}


	public void setSociete(Societe societe) {
		this.societe = societe;
	}


	public int getID_ExpPro() {
		return ID_ExpPro;
	}


	public void addExpertise(Expertise e) {
		if(!lstExpertise.contains(e)) {
			lstExpertise.add(e);
		}
	}
	
	public void fill() {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("ExperiencePro_getByID", 1);
			b.setParamInt(colID, ID_ExpPro);
			ResultSet r = b.executeQuery();
			if(r.next()) {
				
			}
			while(r.next()) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
	}

	public Integer getID() {
		return ID_ExpPro;
	}
}

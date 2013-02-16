package com.polytech.propps.models;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import com.polytech.propps.bdd.Base;

public class Membre extends Utilisateur{
	private final static String colID = "ID_Utilisateur";
	private final static String colProfil = "ID_Profil";
	private final static String colPresta = "bPresta";
	private final static String colDtPresta = "dtFinPresta";
	private final static String colContrat = "bEstSousContrat";
	
	protected int ID_Profil;
	protected boolean bContrat, bPresta;
	protected Date dtFinPresta;
	protected ArrayList<Membre> lstContacts;
	protected ArrayList<ExperiencePro> lstExperiencePro;
	
	public Membre(int ID) {
		super(ID);
	}
	
	@Override
	public void insertOrUpdate() {
		Base b = new Base();
		try {
			super.insertOrUpdate();
			b.connect();
			b.procedureInit("Membre_insertOrUpdate", 5);
			b.setParamInt("_" + colID, super.ID_Utilisateur);
			b.setParamBool("_" + colContrat, bContrat);
			b.setParamBool("_" + colPresta, bPresta);
			b.setParamInt("_" + colProfil, ID_Profil);
			b.setParamDate("_" + colDtPresta, dtFinPresta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
		
	}

	@Override
	public void delete() {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Membre_delete", 1);
			b.setParamInt("_" + colID, super.ID_Utilisateur);			
			super.delete();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
		
	}

}

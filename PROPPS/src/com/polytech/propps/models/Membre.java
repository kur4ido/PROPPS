package com.polytech.propps.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.polytech.propps.bdd.Base;

public class Membre extends Utilisateur{
	private final static String colProfil = "ID_Profil";
	private final static String colPresta = "bPresta";
	private final static String colDtPresta = "dtFinPresta";
	private final static String colContrat = "bEstSousContrat";
	private final static String colNbExp = "nbExp";
	
	protected Profil profil;
	protected boolean bContrat, bPresta;
	protected Date dtFinPresta;
	protected ArrayList<Membre> lstContacts;
	protected ArrayList<ExperiencePro> lstExperiencePro;
	protected ArrayList<Expertise> lstExpertise;
	
	private boolean bFill; 
	
	public Membre(int ID) {
		super(ID);
		bFill = false;
		lstContacts = new ArrayList<Membre>();
		lstExperiencePro = new ArrayList<ExperiencePro>();
		lstExpertise = new ArrayList<Expertise>();
	}
	
	public Membre(String sNom,String sPrenom,String sEmail, String sPassword,Profil profil, boolean bContrat,boolean bPresta,
			Date dtFinPresta) {
		super(sNom,sPrenom,sEmail,sPassword);
		this.profil = profil;
		this.bContrat = bContrat;
		this.bPresta = bPresta;
		this.dtFinPresta = dtFinPresta;
		lstContacts = new ArrayList<Membre>();
		lstExperiencePro = new ArrayList<ExperiencePro>();
		lstExpertise = new ArrayList<Expertise>();
		bFill = true;
	}
	
	public void fill() {
		Base b = new Base();
		try {
			super.insertOrUpdate();
			b.connect();
			b.procedureInit("Membre_getByID", 1);
			b.setParamInt("_" + colID, super.ID_Utilisateur);
			ResultSet result = b.executeQuery();
			if(result.next()) {
				super.sNom = result.getString(colNom);
				super.sPrenom = result.getString(colPrenom);
				super.sEmail = result.getString(colEmail);
				super.sPassword = result.getString(colPassword);
				profil = new Profil(result.getInt(colProfil));
				bContrat = result.getBoolean(colContrat);
				bPresta = result.getBoolean(colPresta);
				dtFinPresta = result.getDate(colDtPresta);
				if(result.getInt(colNbExp) > 0) {
					ExperiencePro ep = new ExperiencePro(result.getInt(ExperiencePro.colID));
					lstExperiencePro.add(ep);
				}
			}
			while(result.next()) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
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
			b.setParamInt("_" + colProfil, profil.getID());
			b.setParamDate("_" + colDtPresta, dtFinPresta);
			ResultSet result = b.executeQuery();
			if(result.next()) {
				super.ID_Utilisateur = result.getInt(colID);
			}
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
			b.execute();
			super.delete();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
		
	}

}

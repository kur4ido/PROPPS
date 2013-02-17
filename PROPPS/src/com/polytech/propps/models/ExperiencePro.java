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
	protected int ID_ExpPro;
	
	private boolean bFill;
	
	public ExperiencePro(int ID) {
		bFill = false;
		this.ID_ExpPro = ID;
		lstExpertise = new ArrayList<Expertise>();
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

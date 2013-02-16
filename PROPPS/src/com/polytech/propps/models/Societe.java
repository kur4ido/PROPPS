package com.polytech.propps.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.polytech.propps.bdd.Base;

public class Societe implements IModel {
	protected static ArrayList<Societe> listOfSociete;
	private final static String colID = "ID_Societe";
	private final static String colNom = "sNom";
	
	protected int ID_Societe;
	protected String sNom;
	
	private boolean bFill;
	
	public Societe(int ID) {
		ID_Societe = ID;
		bFill = false;
	}
	
	public Societe(String sNom) {
		this.sNom = sNom;
		ID_Societe = -1;
		bFill = true;
	}
	
	private Societe(int ID, String sNom) {
		this(sNom);
		ID_Societe = ID;
	}
	
	
	
	protected static void fillList() {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Societe_getAll", 0);
			ResultSet result = b.executeQuery();
			while(result.next()) {
				listOfSociete.add(new Societe(result.getInt(colID),result.getString(colNom)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
	}
	
	public String getNom() {
		if (!bFill) {
			fill();
		}
		return sNom;
	}
	
	private void fill() {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("Societe_getByID", 1);
			b.setParamInt("_" + colID, ID_Societe);
			ResultSet result = b.executeQuery();
			if(result.next()) {
				sNom = result.getString(colNom);
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
			b.connect();
			b.procedureInit("Societe_insertOrUpdate", 2);
			b.setParamInt(colID, ID_Societe);
			b.setParamString(colNom, sNom);
			b.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			b.close();
		}
		
	}

	@Override
	public void delete() {
		// Non implémenté
	}
	
}

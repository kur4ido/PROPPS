package com.polytech.propps.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

import com.polytech.propps.bdd.Base;
import com.polytech.propps.utilsTest.Comparaison;

public class Recruteur extends Utilisateur {
	
	private final static String colID = "ID_Utilisateur";
	private final static String colDureeMin = "dtDispo"; 
	
	protected Societe societe;

	public Recruteur(int ID) {
		super(ID);
		societe = null;
	}
	
	public Recruteur(String sNom, String sPrenom, String sEmail, String sPassword, Societe societe) {
		super(sNom, sPrenom, sEmail, sPassword);
		this.societe = societe;
	}

	@Override
	public void insertOrUpdate() {
		Base b = new Base();
		try {
			super.insertOrUpdate();
			b.connect();
			b.procedureInit("Recruteur_insertOrUpdate", 2);
			b.setParamInt("_" + colID, super.ID_Utilisateur);
			b.setParamInt("_" + Societe.colID, societe.getID());
			b.execute();
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
			b.execute();
			super.delete();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			b.close();
		}
		
	} 

	public Societe getSociete() {
		if(!super.bFill) {
			fill();
		}
		return societe;
	}

	public void setSociete(Societe newSociete) {
		this.societe = newSociete;
		super.bFill = true;
	}


	@Override
	public void fill() {
		if(!super.bFill) {
			Base b = new Base();
			try {
				b.connect();
				b.procedureInit("Recruteur_getByID", 1);
				b.setParamInt("_" + colID, super.ID_Utilisateur);
				ResultSet result = b.executeQuery();
				if(result.next()) {
					super.sNom = result.getString(colNom);
					super.sPrenom = result.getString(colPrenom);
					super.sEmail = result.getString(colEmail);
					super.sPassword = result.getString(colPassword);
					super.adresse.setAdresse(result.getString(Adresse.colAdresse));
					super.adresse.setVille(result.getString(Adresse.colVille));
					super.adresse.setCodePostal(result.getString(Adresse.colCP));
					super.adresse.setPays(result.getString(Adresse.colPays));
					
					societe = new Societe(result.getInt(Societe.colID));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				b.close();
			}
			bFill = true;
		}
		
	}
	
	public ArrayList<Membre> rechercheAvancee(ArrayList<Expertise> lstExpertise, Profil p, boolean bPresta,Date dureeMin) {
		Base b = new Base();
		ArrayList<Membre> lstMembre = new ArrayList<Membre>();
		try {
			b.connect();
			
			for(int i = 0 ; i < lstExpertise.size() ; i++) {
				ArrayList<Membre> lstTemp = new ArrayList<Membre>();
				Expertise e = lstExpertise.get(i);
				b.procedureInit("Recruteur_rechercher", 4);
				b.setParamInt("_" + Expertise.colID, e.getID());
				b.setParamInt("_" + Profil.colID, (p == null ? null : p.getID()));
				b.setParamDate("_" + colDureeMin, dureeMin);
				b.setParamBool("_" + Membre.colPresta, bPresta);
				ResultSet r = b.executeQuery();
				while(r.next()) {
					Membre m = new Membre(r.getInt(Utilisateur.colID));
					if(i == 0) {
						lstMembre.add(m);
					}
					lstTemp.add(m);
				}
				for(Membre m : lstMembre) {
					if(!lstTemp.contains(m)) {
						lstMembre.remove(m);
					}
				}
			}
			Collections.sort(lstMembre, new Comparator<Membre>() {
				@Override
				public int compare(Membre m1, Membre m2) {
					return m1.getExperience() + m1.getScore(societe) - m2.getExperience() - m2.getScore(societe);
				}
				
			});
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			b.close();
		}
		return lstMembre;
	}

}

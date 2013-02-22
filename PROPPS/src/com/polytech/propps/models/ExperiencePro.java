package com.polytech.propps.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.polytech.propps.bdd.Base;

public class ExperiencePro {
	public static final String colID = "ID_ExperiencePro";
	public static final String colNbExpertise = "NbExpertise";
	public static final String colDtDebut = "dtDebut";
	public static final String colDtFin = "dtFin";
	public static final String colDirection = "sDirection";
	public static final String colPosteOccupe = "sPosteOccupe";
	public static final String colDescription = "sDescription";
	
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


	public void addExpertise(Expertise e) {
		if(!lstExpertise.contains(e)) {
			lstExpertise.add(e);
		}
	}
	
	public List getlstExpertise(){
		System.out.println(lstExpertise.toString());
		return Arrays.asList(lstExpertise.toArray());
	}
	
	public void fill() {
		if(!bFill) {
			Base b = new Base();
			try {
				b.connect();
				b.procedureInit("ExperiencePro_getByID", 1);
				b.setParamInt("_" + colID, ID_ExpPro);
				ResultSet result = b.executeQuery();
				if(result.next()) {
					sPosteOccupe = result.getString(colPosteOccupe);
					sDescription = result.getString(colDescription);
					sDirection = result.getString(colDirection);
					dtFin = result.getDate(colDtFin);
					dtDebut = result.getDate(colDtDebut);
					profil = (result.getObject(Profil.colID) == null ? null : new Profil(result.getInt(Profil.colID)));
					societe = new Societe(result.getInt(Societe.colID));
					lstExpertise = new ArrayList<Expertise>();
					if(result.getInt(colNbExpertise) > 0) {
						Expertise e = new Expertise(result.getInt(Expertise.colID));
						lstExpertise.add(e);
					}
				}
				while(result.next()) {
					Expertise e = new Expertise(result.getInt(Expertise.colID));
					lstExpertise.add(e);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				b.close();
			}
			bFill = true;
		}
	}
	
	public void delete() {
		Base b = new Base();
		try {
			b.connect();
			b.procedureInit("ExperiencePro_delete", 1);
			b.setParamInt("_" + colID, ID_ExpPro);
			b.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			b.close();
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof ExperiencePro) {
			ExperiencePro e = (ExperiencePro) o;
			if(ID_ExpPro > 0) {
				return e.getID() == this.ID_ExpPro;
			}else {
				return (societe.getID() == e.societe.getID()) && (e.profil.equals(profil)) && (e.sPosteOccupe.equals(sPosteOccupe))
						&& (e.sDirection.equals(sDirection)) && (e.dtDebut.equals(dtDebut)) && (e.dtFin.equals(dtFin));
			}
		}
		return false;
	}
	
	/*---Getters et Setters---*/

	public Integer getID() {
		return ID_ExpPro;
	}
	
	public void setID(int ID) {
		this.ID_ExpPro = ID;
		bFill = true;
	}
	
	public String getsPosteOccupe() {
		if(!bFill) {
			fill();
		}
		return sPosteOccupe;
	}


	public void setPosteOccupe(String sPosteOccupe) {
		this.sPosteOccupe = sPosteOccupe;
		bFill = true;
	}


	public String getsDescription() {
		if(!bFill) {
			fill();
		}
		return sDescription;
	}


	public void setDescription(String sDescription) {
		this.sDescription = sDescription;
		bFill = true;
	}


	public String getsDirection() {
		if(!bFill) {
			fill();
		}
		return sDirection;
	}


	public void setDirection(String sDirection) {
		this.sDirection = sDirection;
		bFill = true;
	}


	public Date getDtDebut() {
		if(!bFill) {
			fill();
		}
		return dtDebut;
	}


	public void setDtDebut(Date dtDebut) {
		this.dtDebut = dtDebut;
		bFill = true;
	}


	public Date getDtFin() {
		if(!bFill) {
			fill();
		}
		return dtFin;
	}


	public void setDtFin(Date dtFin) {
		this.dtFin = dtFin;
		bFill = true;
	}


	public Profil getProfil() {
		if(!bFill) {
			fill();
		}
		return profil;
	}


	public void setProfil(Profil profil) {
		this.profil = profil;
		bFill = true;
	}


	public Societe getSociete() {
		if(!bFill) {
			fill();
		}
		return societe;
	}


	public void setSociete(Societe societe) {
		this.societe = societe;
		bFill = true;
	}


	public ArrayList<Expertise> getlistExpertise() {
		return lstExpertise;
	}
}

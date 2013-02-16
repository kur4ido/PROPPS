package com.polytech.propps.models;

import java.sql.Date;
import java.util.ArrayList;

public class ExperiencePro {
	
	protected String sPosteOccupe;
	protected String sDescription;
	protected String sDirection;
	protected Date dtDebut;
	protected Date dtFin;
	protected ArrayList<Integer> lstProfils;
	protected int ID_Profil;
	protected int ID_ExpPro;
	
	private boolean bFill;
	
	public ExperiencePro(int ID) {
		bFill = false;
		this.ID_ExpPro = ID;
	}
}

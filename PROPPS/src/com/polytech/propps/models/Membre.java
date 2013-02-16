package com.polytech.propps.models;

import java.util.ArrayList;

public class Membre extends Utilisateur{
	
	protected int ID_Profil;
	protected ArrayList<Membre> lstContacts;
	protected ArrayList<ExperiencePro> lstExperiencePro;
	
	public Membre(int ID) {
		super(ID);
	}
	
	@Override
	public void insertOrUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}

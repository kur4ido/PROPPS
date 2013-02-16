package com.polytech.propps.models;

public abstract class Utilisateur implements IModel {
	
	protected int ID_Membre;
	protected String sNom, sPrenom,sEmail;

	public Utilisateur(int ID) {
		ID_Membre = ID;
	}
	
}

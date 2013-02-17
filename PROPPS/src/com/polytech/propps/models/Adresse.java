package com.polytech.propps.models;

public class Adresse {
	public static final String colID = "ID_Adresse";
	public static final String colVille = "sVille";
	public static final String colCP = "sCodePostal";
	public static final String colAdresse = "sAdresse";
	public static final String colPays = "sPays";

	protected int ID_Adresse, ID_Utilisateur;
	protected String sVille;
	protected String sCodePostal;
	protected String sAdresse;
	protected String sPays;

	public Adresse(String sVille, String sCodePostal, String sAdresse,
			String sPays) {
		this.sAdresse = sAdresse;
		this.sCodePostal = sCodePostal;
		this.sPays = sPays;
		this.sVille = sVille;
	}

	public String getVille() {
		return sVille;
	}

	public void setVille(String sVille) {
		this.sVille = sVille;
	}

	public String getCodePostal() {
		return sCodePostal;
	}

	public void setCodePostal(String sCodePostal) {
		this.sCodePostal = sCodePostal;
	}

	public String getAdresse() {
		return sAdresse;
	}

	public void setAdresse(String sAdresse) {
		this.sAdresse = sAdresse;
	}

	public String getPays() {
		return sPays;
	}

	public void setPays(String sPays) {
		this.sPays = sPays;
	}

}

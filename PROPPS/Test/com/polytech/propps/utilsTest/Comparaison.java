package com.polytech.propps.utilsTest;

import java.sql.Date;

import org.junit.Assert;

import com.polytech.propps.models.Membre;

public class Comparaison {
	
	public static void comparerDeuxMembres(Membre mX, Membre mY) {
		Assert.assertEquals("L'ID du membre est mal insérée.", mX, mY);
		Assert.assertEquals("L'email du membre est mal insérée.", mX.getsEmail(), mY.getsEmail());
		Assert.assertEquals("Le nom du membre est mal inséré.", mX.getsNom(), mY.getsNom());
		Assert.assertEquals("Le mot de passe du membre est mal inséré.", mX.getsPassword(), mY.getsPassword());
		Assert.assertEquals("Le prénom du membre est mal inséré.", mX.getsPrenom(), mY.getsPrenom());
		Assert.assertEquals("L'adresse du membre est mal insérée.", mX.getAdresse(), mY.getAdresse());
		Assert.assertEquals("La classe du membre est mal insérée.", mX.getClass(), mY.getClass());
		Assert.assertEquals("La date de fin de prestation est mal insérée.", mX.getDtFinPresta(), mY.getDtFinPresta());
		Assert.assertEquals("Le profil du membre est mal inséré.", mX.getProfil(), mY.getProfil());
		
		for(Integer i = 0; i < mX.getLstContacts().size(); i++) {
			Assert.assertEquals("La liste de contacts du membre est mal insérée.", mX.getLstContacts().get(i).getID_Utilisateur(), mY.getLstContacts().get(i).getID_Utilisateur());
		}
		
		for(Integer i = 0; i < mX.getLstExperiencePro().size(); i++) {
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (ID).", mX.getLstExperiencePro().get(i).getID(), mY.getLstExperiencePro().get(i).getID());
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Description).", mX.getLstExperiencePro().get(i).getDescription(), mY.getLstExperiencePro().get(i).getDescription());
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Direction).", mX.getLstExperiencePro().get(i).getDirection(), mY.getLstExperiencePro().get(i).getDirection());
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Poste occupé).", mX.getLstExperiencePro().get(i).getPosteOccupe(), mY.getLstExperiencePro().get(i).getPosteOccupe());
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Class).", mX.getLstExperiencePro().get(i).getClass(), mY.getLstExperiencePro().get(i).getClass());
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Date début).", mX.getLstExperiencePro().get(i).getDtDebut(), mY.getLstExperiencePro().get(i).getDtDebut());
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Date fin).", mX.getLstExperiencePro().get(i).getDtFin(), mY.getLstExperiencePro().get(i).getDtFin());
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Profil).", mX.getLstExperiencePro().get(i).getProfil(), mY.getLstExperiencePro().get(i).getProfil());
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Société).", mX.getLstExperiencePro().get(i).getSociete(), mY.getLstExperiencePro().get(i).getSociete());
		}
		
		for(Integer i = 0; i < mX.getLstExpertise().size(); i++) {
			Assert.assertEquals("La liste d'expertises du membre est mal insérée.", mX.getLstExpertise().get(i).getID(), mY.getLstExpertise().get(i).getID());
		}
	}
	
	public static void comparerDates(Date d1, Date d2) {
		Assert.assertEquals("Les dates sont différentes (jour)",d1.getDay(), d2.getDay());
		Assert.assertEquals("Les dates sont différentes (mois)",d1.getMonth(), d2.getMonth());
		Assert.assertEquals("Les dates sont différentes (année)",d1.getYear(), d2.getYear());
	}
}

package com.polytech.propps.utilsTest;

import java.sql.Date;

import org.junit.Assert;

import com.polytech.propps.models.Membre;
import com.polytech.propps.models.Recruteur;

public class Comparaison {
	
	public static void comparerDeuxMembres(Membre mX, Membre mY) {
		Assert.assertEquals("L'ID du membre est mal insérée.", mX, mY);
		Assert.assertEquals("L'email du membre est mal insérée.", mX.getsEmail(), mY.getsEmail());
		Assert.assertEquals("Le nom du membre est mal inséré.", mX.getsNom(), mY.getsNom());
		//Assert.assertEquals("Le mot de passe du membre est mal inséré.", mX.getsPassword(), mY.getsPassword());
		Assert.assertEquals("Le prénom du membre est mal inséré.", mX.getsPrenom(), mY.getsPrenom());
		Assert.assertEquals("L'adresse du membre est mal insérée. (Adresse)", mX.getAdresse().getAdresse(), mY.getAdresse().getAdresse());
		Assert.assertEquals("L'adresse du membre est mal insérée. (Code Postal)", mX.getAdresse().getCodePostal(), mY.getAdresse().getCodePostal());
		Assert.assertEquals("L'adresse du membre est mal insérée. (Pays)", mX.getAdresse().getPays(), mY.getAdresse().getPays());
		Assert.assertEquals("L'adresse du membre est mal insérée. (Ville)", mX.getAdresse().getVille(), mY.getAdresse().getVille());
		Assert.assertEquals("La date de fin de prestation est mal insérée. (Année)", mX.getDtFinPresta().getYear(), mY.getDtFinPresta().getYear());
		Assert.assertEquals("La date de fin de prestation est mal insérée. (Mois)", mX.getDtFinPresta().getMonth(), mY.getDtFinPresta().getMonth());
		Assert.assertEquals("La date de fin de prestation est mal insérée. (Jour)", mX.getDtFinPresta().getDay(), mY.getDtFinPresta().getDay());
		Assert.assertEquals("Le profil du membre est mal inséré.", mX.getProfil(), mY.getProfil());
		
		for(Integer i = 0; i < mX.getLstContacts().size(); i++) {
			Assert.assertEquals("La liste de contacts du membre est mal insérée.", mX.getLstContacts().get(i).getID_Utilisateur(), mY.getLstContacts().get(i).getID_Utilisateur());
		}
		
		for(Integer i = 0; i < mX.getLstExperiencePro().size(); i++) {
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Description).", mX.getLstExperiencePro().get(i).getDescription(), mY.getLstExperiencePro().get(i).getDescription());
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Direction).", mX.getLstExperiencePro().get(i).getDirection(), mY.getLstExperiencePro().get(i).getDirection());
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Poste occupé).", mX.getLstExperiencePro().get(i).getPosteOccupe(), mY.getLstExperiencePro().get(i).getPosteOccupe());
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Date début).", mX.getLstExperiencePro().get(i).getDtDebut(), mY.getLstExperiencePro().get(i).getDtDebut());
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Date fin).", mX.getLstExperiencePro().get(i).getDtFin(), mY.getLstExperiencePro().get(i).getDtFin());
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Profil).", mX.getLstExperiencePro().get(i).getProfil(), mY.getLstExperiencePro().get(i).getProfil());
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Société).", mX.getLstExperiencePro().get(i).getSociete().getID(), mY.getLstExperiencePro().get(i).getSociete().getID());
			Assert.assertEquals("La liste d'expérience pro du membre est mal insérée (Société).", mX.getLstExperiencePro().get(i).getSociete().getNom(), mY.getLstExperiencePro().get(i).getSociete().getNom());
		}
		
		for(Integer i = 0; i < mX.getLstExpertise().size(); i++) {
			Assert.assertEquals("La liste d'expertises du membre est mal insérée.", mX.getLstExpertise().get(i).getID(), mY.getLstExpertise().get(i).getID());
		}
		
		for(Integer i = 0; i < mX.getLstNotifEnvoi().size(); i++) {
			Assert.assertEquals("La liste de notifications d'envoi est mal insérée. (ID)", mX.getLstNotifEnvoi().get(i).getID(), mY.getLstNotifEnvoi().get(i).getID());
			Assert.assertEquals("La liste de notifications d'envoi est mal insérée. (Destinataire)", mX.getLstNotifEnvoi().get(i).getDestinataire().getID_Utilisateur(), mY.getLstNotifEnvoi().get(i).getDestinataire().getID_Utilisateur());
			Assert.assertEquals("La liste de notifications d'envoi est mal insérée. (DtDemande Année)", mX.getLstNotifEnvoi().get(i).getDtDemande().getYear(), mY.getLstNotifEnvoi().get(i).getDtDemande().getYear());
			Assert.assertEquals("La liste de notifications d'envoi est mal insérée. (DtDemande Mois)", mX.getLstNotifEnvoi().get(i).getDtDemande().getMonth(), mY.getLstNotifEnvoi().get(i).getDtDemande().getMonth());
			Assert.assertEquals("La liste de notifications d'envoi est mal insérée. (DtDemande Jour)", mX.getLstNotifEnvoi().get(i).getDtDemande().getDay(), mY.getLstNotifEnvoi().get(i).getDtDemande().getDay());
			Assert.assertEquals("La liste de notifications d'envoi est mal insérée. (Source)", mX.getLstNotifEnvoi().get(i).getSource().getID_Utilisateur(), mY.getLstNotifEnvoi().get(i).getSource().getID_Utilisateur());
		}
		
		for(Integer i = 0; i < mX.getLstNotifRecept().size(); i++) {
			Assert.assertEquals("La liste de notifactions de réception est mal insérée. (Destinataire)", mX.getLstNotifRecept().get(i).getDestinataire().getID_Utilisateur(), mY.getLstNotifRecept().get(i).getDestinataire().getID_Utilisateur());
			Assert.assertEquals("La liste de notifactions de réception est mal insérée. (DtDemande Année)", mX.getLstNotifRecept().get(i).getDtDemande().getYear(), mY.getLstNotifRecept().get(i).getDtDemande().getYear());
			Assert.assertEquals("La liste de notifactions de réception est mal insérée. (DtDemande Mois)", mX.getLstNotifRecept().get(i).getDtDemande().getMonth(), mY.getLstNotifRecept().get(i).getDtDemande().getMonth());
			Assert.assertEquals("La liste de notifactions de réception est mal insérée. (DtDemande Jour)", mX.getLstNotifRecept().get(i).getDtDemande().getDay(), mY.getLstNotifRecept().get(i).getDtDemande().getDay());
			Assert.assertEquals("La liste de notifactions de réception est mal insérée. (ID)", mX.getLstNotifRecept().get(i).getID(), mY.getLstNotifRecept().get(i).getID());
			Assert.assertEquals("La liste de notifactions de réception est mal insérée. (Source)", mX.getLstNotifRecept().get(i).getSource().getID_Utilisateur(), mY.getLstNotifRecept().get(i).getSource().getID_Utilisateur());
		}
		
		for(Integer i = 0; i < mX.getLstContacts().size(); i++) {
			Assert.assertEquals("La liste de contacts est mal insérée.", mX.getLstContacts().get(i).getID_Utilisateur(), mY.getLstContacts().get(i).getID_Utilisateur());
		}
	}
	
	public static void membreEstVide(Membre mX) {
		Assert.assertNull("L'email du membre a mal été supprimée.", mX.getsEmail());
		Assert.assertNull("Le nom du membre a mal été supprimé.", mX.getsNom());
		Assert.assertNull("Le mot de passe du membre a mal été supprimé.", mX.getsPassword());
		Assert.assertNull("Le prénom du membre a mal été supprimé.", mX.getsPrenom());
		Assert.assertNull("L'adresse du membre a mal été supprimée.", mX.getAdresse().getAdresse());
		Assert.assertNull("L'adresse du membre a mal été supprimée.", mX.getAdresse().getCodePostal());
		Assert.assertNull("L'adresse du membre a mal été supprimée.", mX.getAdresse().getPays());
		Assert.assertNull("L'adresse du membre a mal été supprimée.", mX.getAdresse().getVille());

		Assert.assertNull("La date de fin de prestation a mal été supprimée.", mX.getDtFinPresta());
		Assert.assertNull("Le profil du membre a mal été supprimé.", mX.getProfil());
		
		for(Integer i = 0; i < mX.getLstContacts().size(); i++) {
			Assert.assertNull("La liste de contacts du membre a mal été supprimé.", mX.getLstContacts().get(i).getID_Utilisateur());
		}
		
		for(Integer i = 0; i < mX.getLstExperiencePro().size(); i++) {
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimée (ID).", mX.getLstExperiencePro().get(i).getID());
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimée (Description).", mX.getLstExperiencePro().get(i).getDescription());
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimée (Direction).", mX.getLstExperiencePro().get(i).getDirection());
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimée (Poste occupé).", mX.getLstExperiencePro().get(i).getPosteOccupe());
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimée (Date début).", mX.getLstExperiencePro().get(i).getDtDebut());
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimée (Date fin).", mX.getLstExperiencePro().get(i).getDtFin());
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimée (Profil).", mX.getLstExperiencePro().get(i).getProfil());
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimée (Société).", mX.getLstExperiencePro().get(i).getSociete());
		}
		
		for(Integer i = 0; i < mX.getLstExpertise().size(); i++) {
			Assert.assertNull("La liste d'expertises du membre a mal été supprimé. (ID)", mX.getLstExpertise().get(i).getID());
			Assert.assertNull("La liste d'expertises du membre a mal été supprimé. (Domaine)", mX.getLstExpertise().get(i).getDomaine());
			Assert.assertNull("La liste d'expertises du membre a mal été supprimé. (Type)", mX.getLstExpertise().get(i).getType());
		}
		
		for(Integer i = 0; i < mX.getLstNotifEnvoi().size(); i++) {
			Assert.assertNull("La liste de notifications d'envoi a mal été supprimée. (ID)", mX.getLstNotifEnvoi().get(i).getID());
			Assert.assertNull("La liste de notifications d'envoi a mal été supprimée. (Destinataire)", mX.getLstNotifEnvoi().get(i).getDestinataire().getID_Utilisateur());
			Assert.assertNull("La liste de notifications d'envoi a mal été supprimée. (DtDemande Année)", mX.getLstNotifEnvoi().get(i).getDtDemande().getYear());
			Assert.assertNull("La liste de notifications d'envoi a mal été supprimée. (DtDemande Mois)", mX.getLstNotifEnvoi().get(i).getDtDemande().getMonth());
			Assert.assertNull("La liste de notifications d'envoi a mal été supprimée. (DtDemande Jour)", mX.getLstNotifEnvoi().get(i).getDtDemande().getDay());
			Assert.assertNull("La liste de notifications d'envoi a mal été supprimée. (Source)", mX.getLstNotifEnvoi().get(i).getSource().getID_Utilisateur());
		}
		
		for(Integer i = 0; i < mX.getLstNotifRecept().size(); i++) {
			Assert.assertNull("La liste de notifactions de réception a mal été supprimée. (Destinataire)", mX.getLstNotifRecept().get(i).getDestinataire().getID_Utilisateur());
			Assert.assertNull("La liste de notifactions de réception a mal été supprimée. (DtDemande Année)", mX.getLstNotifRecept().get(i).getDtDemande().getYear());
			Assert.assertNull("La liste de notifactions de réception a mal été supprimée. (DtDemande Mois)", mX.getLstNotifRecept().get(i).getDtDemande().getMonth());
			Assert.assertNull("La liste de notifactions de réception a mal été supprimée. (DtDemande Jour)", mX.getLstNotifRecept().get(i).getDtDemande().getDay());
			Assert.assertNull("La liste de notifactions de réception a mal été supprimée. (ID)", mX.getLstNotifRecept().get(i).getID());
			Assert.assertNull("La liste de notifactions de réception a mal été supprimée. (Source)", mX.getLstNotifRecept().get(i).getSource().getID_Utilisateur());
		}
		
		for(Integer i = 0; i < mX.getLstContacts().size(); i++) {
			Assert.assertNull("La liste de contacts de a mal été supprimée.", mX.getLstContacts().get(i).getID_Utilisateur());
		}
	}
	
	public static void comparerDates(Date d1, Date d2) {
		Assert.assertEquals("Les dates sont différentes (jour)",d1.getDay(), d2.getDay());
		Assert.assertEquals("Les dates sont différentes (mois)",d1.getMonth(), d2.getMonth());
		Assert.assertEquals("Les dates sont différentes (année)",d1.getYear(), d2.getYear());
	}
	
	public static void comparerDeuxRecruteurs(Recruteur rX, Recruteur rY) {
		Assert.assertEquals("L'email du recruteur est mal inséré.", rX.getsEmail(), rY.getsEmail());
		Assert.assertEquals("Le nom du recruteur est mal inséré.", rX.getsNom(), rY.getsNom());
		Assert.assertEquals("Le prénom du recruteur est mal inséré.", rX.getsPrenom(), rY.getsPrenom());
		Assert.assertEquals("L'adresse du membre est mal insérée. (Adresse)", rX.getAdresse().getAdresse(), rY.getAdresse().getAdresse());
		Assert.assertEquals("L'adresse du membre est mal insérée. (Code Postal)", rX.getAdresse().getCodePostal(), rY.getAdresse().getCodePostal());
		Assert.assertEquals("L'adresse du membre est mal insérée. (Pays)", rX.getAdresse().getPays(), rY.getAdresse().getPays());
		Assert.assertEquals("L'adresse du membre est mal insérée. (Ville)", rX.getAdresse().getVille(), rY.getAdresse().getVille());
		Assert.assertEquals("La société du recruteur est mal insérée. (ID)", rX.getSociete().getID(), rY.getSociete().getID());
		Assert.assertEquals("La société du recruteur est mal insérée. (Nom)", rX.getSociete().getNom(), rY.getSociete().getNom());
	}
	
	public static void RecruteurEstVide(Recruteur rX) {
		Assert.assertNull("L'email du membre a mal été supprimée.", rX.getsEmail());
		Assert.assertNull("Le nom du membre a mal été supprimé.", rX.getsNom());
		Assert.assertNull("Le mot de passe du membre a mal été supprimé.", rX.getsPassword());
		Assert.assertNull("Le prénom du membre a mal été supprimé.", rX.getsPrenom());
		Assert.assertNull("L'adresse du membre a mal été supprimée.", rX.getAdresse().getAdresse());
		Assert.assertNull("La société du recruteur a été mal supprimée", rX.getSociete());
	}
}

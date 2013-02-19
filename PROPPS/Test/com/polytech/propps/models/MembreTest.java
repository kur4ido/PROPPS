package com.polytech.propps.models;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Date;

import org.junit.*;

import com.polytech.propps.appli.PROPPS;

public class MembreTest {
	private Membre m1;
	private Membre m2;
	private Date date;
	
	@BeforeClass
	public static void setUpClass() throws IOException, ClassNotFoundException {
		PROPPS.init();
		
	}
	
	@AfterClass
	public static void tearDownClass() {
		
	}
	
	@Before
	public void setUp() {
		date = new Date(2013, 02, 19);
		m1 = new Membre("Test", "Clément", "clement.test@u-psud.fr", "kubor", new Profil(1), true, false, date);
		m2 = new Membre(2);
	}
	
	@After
	public void tearDown() {
		m1.delete();
	}
	
	@Test
	public void Constructeur1Test() {
		
		// Test de super(ID)
		Assert.assertEquals("La valeur ID n'est pas initialisée correctement.", m2.getID_Utilisateur(), 2);
		
		// Test de l'initialisation de bFill
		Assert.assertFalse("bFill n'est pas initialisé à False.", m2.isbFill());
		
		// Test de l'initialisation de lstContact
		Assert.assertNotNull("lstContact n'est pas initialisée.", m2.getLstContacts());
		Assert.assertEquals("lstContact n'est pas vide.", m2.getLstContacts().size(), 0);
		
		// Test de l'initialisation de lstExperiencePro
		Assert.assertNotNull("lstExperiencePro n'est pas initialisée.", m2.getLstExperiencePro());
		Assert.assertEquals("lstExperiencePro n'est pas vide.", m2.getLstExperiencePro().size(), 0);
		
		// Test de l'initialisation de lstExperience
		Assert.assertNotNull("lstExperience n'est pas initialisée.", m2.getLstExpertise());
		Assert.assertEquals("lstExperience n'est pas vide.", m2.getLstExpertise().size(), 0);
	}
	
	@Test
	public void Constructeur2Test() {
		
		// Test de super(sNom, sPrenom, sEmail, sPassword)
		Assert.assertEquals("La valeur sNom n'est pas initalisée correctement.", m1.getsNom(), "Test");
		Assert.assertEquals("La valeur de sPrenom n'est pas initialisée correctement.", m1.getsPrenom(), "Clément");
		Assert.assertEquals("La valeur de sEmail n'est pas initialisée correctement.", m1.getsEmail(), "clement.test@u-psud.fr");
		Assert.assertEquals("La valeur de sPassword n'est pas initialisée correctement.", m1.getsPassword(), "kubor");
		
		// Test de l'initialisation de profil, bContrat, b.Presta, dtFinPresta
		Assert.assertEquals("La valeur de profil n'est pas initialisée correctement.", m1.getProfil(), new Profil(1));
		Assert.assertTrue("La valeur de bContrat n'est pas initialisée correctement.", m1.hasContrat());
		Assert.assertFalse("La valeur de bPresta n'est pas initialisée correctement.", m1.isPresta());
		Assert.assertEquals("La valeur de btFinPresta n'est pas initialisée correctement.", m1.getDtFinPresta(), date);
		
		// Test de l'initialisation de lstContact
		Assert.assertNotNull("lstContact n'est pas initialisée.", m1.getLstContacts());
		Assert.assertEquals("lstContact n'est pas vide.", m1.getLstContacts().size(), 0);
		
		// Test de l'initialisation de lstExperiencePro
		Assert.assertNotNull("lstExperiencePro n'est pas initialisée.", m1.getLstExperiencePro());
		Assert.assertEquals("lstExperiencePro n'est pas vide.", m1.getLstExperiencePro().size(), 0);
		
		// Test de l'initialisation de lstExperience
		Assert.assertNotNull("lstExperience n'est pas initialisée.", m1.getLstExpertise());
		Assert.assertEquals("lstExperience n'est pas vide.", m1.getLstExpertise().size(), 0);
		
		// Test de l'initialisation de bFill
		Assert.assertTrue("bFill n'est pas initialisée à True.", m1.isbFill());
	}
	
	@Test
	public void fillTest() {
		Membre m4 = new Membre(m1.getID_Utilisateur());
		m4.fill();
		Assert.assertEquals(m1, m4);
		m4.delete();
	}
	
	@Test
	public void insertOrUpdateTest() {
		m1.insertOrUpdate();
		Membre m5 = new Membre(m1.getID_Utilisateur());
		comparerDeuxMembres(m1, m5);
		m5.delete();
	}
	
	public void comparerDeuxMembres(Membre mX, Membre mY) {
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
	
	@Test
	public void deleteTest() {
		// Vérifier qu'il est bien dans la base
		Integer indiceBase = m1.getID_Utilisateur();
		Membre m6 = new Membre(m1.getID_Utilisateur());
		comparerDeuxMembres(m1, m6);
		m6.delete();
		
		// delete
		m1.delete();
		
		// checker à l'indice de m1 il n'y a plus rien
		Membre m7 = new Membre(indiceBase);
		membreEstVide(m7);
			
	}
	
	public void membreEstVide(Membre mX) {
		Assert.assertNull("Le membre a mal été supprimé.", mX.getID_Utilisateur());
		Assert.assertNull("L'email du membre a mal été supprimé.", mX.getsEmail());
		Assert.assertNull("Le nom du membre a mal été supprimé.", mX.getsNom());
		Assert.assertNull("Le mot de passe du membre a mal été supprimé.", mX.getsPassword());
		Assert.assertNull("Le prénom du membre a mal été supprimé.", mX.getsPrenom());
		Assert.assertNull("L'adresse du membre a mal été supprimé.", mX.getAdresse());
		Assert.assertNull("La classe du membre a mal été supprimé.", mX.getClass());
		Assert.assertNull("La date de fin de prestation a mal été supprimé.", mX.getDtFinPresta());
		Assert.assertNull("Le profil du membre a mal été supprimé.", mX.getProfil());
		
		for(Integer i = 0; i < mX.getLstContacts().size(); i++) {
			Assert.assertNull("La liste de contacts du membre a mal été supprimé.", mX.getLstContacts().get(i).getID_Utilisateur());
		}
		
		for(Integer i = 0; i < mX.getLstExperiencePro().size(); i++) {
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimé (ID).", mX.getLstExperiencePro().get(i).getID());
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimé (Description).", mX.getLstExperiencePro().get(i).getDescription());
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimé (Direction).", mX.getLstExperiencePro().get(i).getDirection());
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimé (Poste occupé).", mX.getLstExperiencePro().get(i).getPosteOccupe());
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimé (Class).", mX.getLstExperiencePro().get(i).getClass());
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimé (Date début).", mX.getLstExperiencePro().get(i).getDtDebut());
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimé (Date fin).", mX.getLstExperiencePro().get(i).getDtFin());
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimé (Profil).", mX.getLstExperiencePro().get(i).getProfil());
			Assert.assertNull("La liste d'expérience pro du membre a mal été supprimé (Société).", mX.getLstExperiencePro().get(i).getSociete());
		}
		
		for(Integer i = 0; i < mX.getLstExpertise().size(); i++) {
			Assert.assertNull("La liste d'expertises du membre a mal été supprimé.", mX.getLstExpertise().get(i).getID());
		}
	}
}

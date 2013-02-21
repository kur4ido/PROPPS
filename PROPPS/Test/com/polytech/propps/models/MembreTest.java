package com.polytech.propps.models;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Date;

import org.junit.*;

import com.polytech.propps.appli.PROPPS;
import com.polytech.propps.utilsTest.Comparaison;

public class MembreTest {
	private Membre m1;
	private Membre m2;
	private Date date;
	private Expertise expertise;
	private ExperiencePro xpPro;
	private Date dateDebutPro;
	private Societe societeTest;
	private Profil profilTest;
	
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
		dateDebutPro = new Date(2013, 01, 21);
		m1 = new Membre("Test", "Clément", "clement.test@u-psud.fr", "kubor", new Profil(1), true, false, date);
		m2 = new Membre(m1.getID_Utilisateur());
		expertise = new Expertise(1);
		societeTest = new Societe(1);
		profilTest = new Profil(1);
		xpPro = new ExperiencePro(-1);
		xpPro.setDescription("Description test.");
		xpPro.setDirection("Direction test.");
		xpPro.setDtDebut(dateDebutPro);
		xpPro.setDtFin(null);
		xpPro.setPosteOccupe("Post occupé test.");
		xpPro.setProfil(profilTest);
		xpPro.setSociete(societeTest);
	}
	
	@After
	public void tearDown() {
		m1.delete();
		m2.delete();
	}
	
	@Test
	public void Constructeur1Test() {
		
		// Test de super(ID)
		Assert.assertEquals("La valeur ID n'est pas initialisée correctement.", m2.getID_Utilisateur(), m1.getID_Utilisateur());
		
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
	/**
	 * Les tests fillExpertise(), fillContact(), fillNotification()
	 * sont implicitement testés dans comparerDeuxRecruteurs
	 */
	public void fillTest() {
		m1.insertOrUpdate();
		Membre m4 = new Membre(m1.getID_Utilisateur());
		m4.fill();
		Comparaison.comparerDeuxMembres(m1, m4);
		
	}
	
	@Test
	public void insertOrUpdateTest() {
		
		//test insertion
		m1.insertOrUpdate();
		Membre m5 = new Membre(m1.getID_Utilisateur());
		m5.fill();
		Comparaison.comparerDeuxMembres(m1, m5);
		
		//tests update
		m1.setsPrenom("Mathieu");
		m1.insertOrUpdate();
		Membre m7 = new Membre(m1.getID_Utilisateur());
		m7.fill();
		Comparaison.comparerDeuxMembres(m1, m7);
		
	}
	
	
	
	@Test
	public void deleteTest() {
		// Vérifier qu'il est bien dans la base
		Integer indiceBase = m1.getID_Utilisateur();
		m1.insertOrUpdate();
		Membre m6 = new Membre(m1.getID_Utilisateur());
		m6.fill();
		Comparaison.comparerDeuxMembres(m1, m6);
		
		
		// delete
		m1.delete();
		
		// checker à l'indice de m1 il n'y a plus rien
		Membre m7 = new Membre(indiceBase);
		m7.fill();
		Comparaison.membreEstVide(m7);
	}
	
	
	
	@Test
	public void ajoutExperienceProTest() {
		m1.insertOrUpdate();
		m1.addExperiencePro(xpPro);
		m1.insertOrUpdate();
		Membre m8 = new Membre(m1.getID_Utilisateur());
		m8.fill();
		Comparaison.comparerDeuxMembres(m1, m8);
	}
	
	@Test
	public void ajoutExpertiseTest() {
		
		m1.insertOrUpdate();
		m1.addExpertise(expertise);
		m1.insertOrUpdate();
		Membre m7 = new Membre(m1.getID_Utilisateur());
		m7.fill();
		Comparaison.comparerDeuxMembres(m1, m7);
	}
	
}

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
		Assert.assertNotNull("lstContact n'est pas initialisé.", m2.getLstContacts());
		Assert.assertEquals("lstContact n'est pas vide.", m2.getLstContacts().size(), 0);
		
		// Test de l'initialisation de lstExperiencePro
		Assert.assertNotNull("lstExperiencePro n'est pas initialisé.", m2.getLstExperiencePro());
		Assert.assertEquals("lstExperiencePro n'est pas vide.", m2.getLstExperiencePro().size(), 0);
		
		// Test de l'initialisation de lstExperience
		Assert.assertNotNull("lstExperience n'est pas initialisé.", m2.getLstExpertise());
		Assert.assertEquals("lstExperience n'est pas vide.", m2.getLstExpertise().size(), 0);
	}
	
	public void Constructeur2Test() {
		
		// Test de super(sNom, sPrenom, sEmail, sPassword)
		Assert.assertEquals("La valeur sNom n'est pas initalisée correctement.", m1.getsNom(), "Test");
		Assert.assertEquals("La valeur de sPrenom n'est pas initialisée correctement.", m1.getsPrenom(), "Clément");
		Assert.assertEquals("La valeur de sEmail n'est pas initialisée correctement.", m1.getsEmail(), "clement.test@u-psud.fr");
		Assert.assertEquals("La valeur de sPassword n'est pas initialisée correctement.", m1.getsPassword(), "kubor");
		
		// Test de l'initialisation de profil, bContrat, b.Presta, dtFinPresta
		Assert.assertEquals("La valeur de profil n'est pas initialisée correctement.", m1.getProfil(), new Profil(1));
		Assert.assertFalse("La valeur de bContrat n'est pas initialisée correctement.", m1.hasContrat());
		Assert.assertTrue("La valeur de bPresta n'est pas initialisée correctement.", m1.isPresta());
		Assert.assertEquals("La valeur de btFinPresta n'est pas initialisée correctement.", m1.getDtFinPresta(), date);
		
		// Test de l'initialisation de lstContact
		Assert.assertNotNull("lstContact n'est pas initialisé.", m1.getLstContacts());
		Assert.assertEquals("lstContact n'est pas vide.", m1.getLstContacts().size(), 0);
		
		// Test de l'initialisation de lstExperiencePro
		Assert.assertNotNull("lstExperiencePro n'est pas initialisé.", m1.getLstExperiencePro());
		Assert.assertEquals("lstExperiencePro n'est pas vide.", m1.getLstExperiencePro().size(), 0);
		
		// Test de l'initialisation de lstExperience
		Assert.assertNotNull("lstExperience n'est pas initialisé.", m1.getLstExpertise());
		Assert.assertEquals("lstExperience n'est pas vide.", m1.getLstExpertise().size(), 0);
		
		// Test de l'initialisation de bFill
		Assert.assertTrue("bFill n'est pas initialisé à False.", m1.isbFill());
	}
	
	public void fillTest() {
		Membre m4 = new Membre(m1.getID_Utilisateur());
		m4.fill();
		assertEquals(m1, m4);
		m4.delete();
	}
	
	public void insertOrUpdateTest() {
		m1.insertOrUpdate();
	}
	
	public void deleteTest() {
		m1.delete();
	}
}

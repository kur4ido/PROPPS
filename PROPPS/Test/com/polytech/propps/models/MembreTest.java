package com.polytech.propps.models;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.jasper.tagplugins.jstl.core.Set;
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
	
	@Test
	public void demanderContactTest() {
		m1.insertOrUpdate();
		Membre m9 = new Membre("Test", "Jean", "jean.test@u-psud.fr", "kubor", new Profil(1), true, false, date);
		m9.insertOrUpdate();
		m1.demanderContact(m9);
		
		ArrayList<Notification> arraylist = new ArrayList<Notification>();
		HashMap<Integer,Notification> hashmap = new HashMap<Integer,Notification>();
		hashmap = m1.getLstNotifEnvoi();
		for(Map.Entry<Integer, Notification> e : hashmap.entrySet()) {
			arraylist.add(e.getValue());
		}
		
		assertEquals("La notification de demande de contact n'est pas unique.", arraylist.size(), 1);
		Notification n = arraylist.get(0);
		assertEquals("Le destinataire de la demande de contact est erroné.", n.getDestinataire(), m9);
		assertEquals("La source de la notification de demande de contact est erronée.", n.getSource(), m1);
		Assert.assertFalse("L'ID de la notification de demande de contact est erroné.", n.getID() == -1);
		assertNotNull("La date de demande de contact est erroné.", n.getDtDemande());
		assertFalse("isbAccept n'est pas à la False.", n.isbAccept());
		assertFalse("isbVuDest n'est pas à False.", n.isbVuDest());
		assertFalse("isbVuSource n'est pas à False.", n.isbVuSource());
		m9.delete();
	}

	@Test
	public void reponseDemandeTestMembreTrue() {
		m1.insertOrUpdate();
		
		Membre m10 = new Membre("Test", "Arnaud", "arnaud.test@u-psud.fr", "kubor", new Profil(1), true, false, date);
		m10.insertOrUpdate();
		m1.demanderContact(m10);
		m10.reponseDemande(m1, true);
		
		// doit se retrouver dans ta liste d'amis
		assertTrue("m1 ne se trouve pas dans la liste d'amis de m10.", m10.getLstContacts().contains(m1));

		m10.delete();
	}
	
	@Test
	public void reponseDemandeTestMembreFalse() {
		m1.insertOrUpdate();
		
		Membre m11 = new Membre("Test", "Alexandre", "alexandre.test@u-psud.fr", "kubor", new Profil(1), true, false, date);
		m11.insertOrUpdate();
		m1.demanderContact(m11);
		m11.reponseDemande(m1, false);
		
		// ne doit pas se retrouver dans sa liste d'amis
		assertFalse("m1 se trouve dans l'amis de m11 alors qu'il ne devrait pas.", m11.getLstContacts().contains(m1));
		
		m11.delete();
	}
	
	@Test
	public void addContactTest() {
		m1.insertOrUpdate();
		Membre m12 = new Membre("Test", "Albert", "albert.test@u-psud.fr", "kubor", new Profil(1), true, false, date);
		m12.insertOrUpdate();
		m1.addContact(m12);
		assertTrue("m12 ne se trouve dans l'amis de m1.", m1.getLstContacts().contains(m12));
		
		m12.delete();
		
	}
	
	@Test
	public void aCommeAmiTest() {
		m1.insertOrUpdate();
		Membre m13 = new Membre("Test", "Da", "da.test@u-psud.fr", "kubor", new Profil(1), true, false, date);
		m13.insertOrUpdate();
		m1.addContact(m13);
		assertTrue("m1 n'a pas comme ami m13", m1.aCommeAmi(m13));
		
		m13.delete();
	}
	
	@Test
	public void aEnvoyeInvitTest() {
		m1.insertOrUpdate();
		Membre m14 = new Membre("Test", "Druno", "bruno.test@u-psud.fr", "kubor", new Profil(1), true, false, date);
		m14.insertOrUpdate();
		m1.demanderContact(m14);
		assertTrue("m1 n'a pas envoyé l'invitation à m14", m1.aEnvoyeInvit(m14));
		
		m14.delete();
	}
}

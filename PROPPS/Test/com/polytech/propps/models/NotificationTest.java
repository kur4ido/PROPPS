package com.polytech.propps.models;

import java.io.IOException;
import java.sql.Date;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.polytech.propps.appli.PROPPS;
import com.polytech.propps.utilsTest.Comparaison;

public class NotificationTest {
	
	private Notification notif;
	private Membre m1, m2;
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
		
		date = new Date(2013, 02, 22);
		
		m1 = new Membre("NOM1", "pnom1", "me1@me.fr", "pwd1", new Profil(1), false, false, date);
		m2 = new Membre("NOM2", "pnom2", "me2@me.fr", "pwd2", new Profil(2), false, false, date);
		
		notif = new Notification( 1, m1, m2, date, true,
				false, false);
	}
	
	@After
	public void tearDown() {
	
	}
	
	@Test
	public void ConstructeurNotificationTest(){
		
		Assert.assertEquals("L'indice de notification n'est pas initilisé correctement", 1, notif.getID());
		
		Comparaison.comparerDeuxMembres(m1, notif.getSource());
		Comparaison.comparerDeuxMembres(m2, notif.getDestinataire());
		
		Assert.assertEquals("La date du message n'est pas initialisé correctement.", date.getYear(), notif.getDtDemande().getYear() );
		Assert.assertEquals("La date du message n'est pas initialisé correctement.", date.getMonth(), notif.getDtDemande().getMonth());
		Assert.assertEquals("La date du message n'est pas initialisé correctement.", date.getDay(), notif.getDtDemande().getDay());
		
		Assert.assertTrue("bVuSource n'est pas initilisé correctement", notif.isbVuSource());
		Assert.assertFalse("bVuDest n'est pas initilisé correctement", notif.isbVuDest());
		Assert.assertFalse("bAccept n'est pas initilisé correctement", notif.isbAccept());
		
		
	}
	
	@Test
	public void settersNotificationTest(){
		
		notif.setbVuSource(false);
		Assert.assertFalse("bVuSource n'est pas initilisé correctement", notif.isbVuSource());
		
		notif.setbVuDest(true);
		Assert.assertTrue("bVuDest n'est pas initilisé correctement", notif.isbVuDest());
		
		notif.setbAccept(true);
		Assert.assertTrue("bAccept n'est pas initilisé correctement", notif.isbAccept());
		
	}

}

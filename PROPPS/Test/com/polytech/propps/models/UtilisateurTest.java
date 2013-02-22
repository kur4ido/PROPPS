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

public class UtilisateurTest {
	
	private Utilisateur u1;
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
		date = new Date(2013, 2, 22);
		u1 = new Membre("Nom1", "pnom1", "me1@me.fr", "pwd1.0", new Profil(1), true, true, date);
		u1.insertOrUpdate();
	}
	
	@After
	public void tearDown() {
		u1.delete();
	}
	
	@Test
	public void updateUserPwdTest(){
		
		Utilisateur u2 = new Membre(u1.getID_Utilisateur());
		u2.fill();
		
		u1.setPassword("toto");
		u1.updatePassWord();
		
		Utilisateur u3 = new Membre(u1.getID_Utilisateur());
		u3.fill();
		
		Assert.assertFalse("Le pwd n'a pas été changé dans la base", u2.getsPassword() == u3.getsPassword());
		
		
	}
	
	
	@Test
	public void addMessage_and_fillMessage_Test(){
		
		
		u1.addMessage(new Message(-1, "content", u1.getID_Utilisateur(), date));
		Utilisateur u2 = new Membre(u1.getID_Utilisateur());
		u2.fillMessages();
		
		Assert.assertEquals("Il devrait y avoir un message dans la liste.", 1, u2.lstMessage.size());
		
		u2.lstMessage.get(0).delete();
		
	}

}

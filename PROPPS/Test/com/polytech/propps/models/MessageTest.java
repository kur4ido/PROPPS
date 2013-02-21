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

public class MessageTest {
	
	private Message msg;
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
		date = new Date(2013, 02, 21);
		msg = new Message(1, "content", 2, date);
	}
	
	@After
	public void tearDown() {
	
	}
	
	@Test
	public void ConstructeurMessageTest(){
		
		Assert.assertEquals("L'id message n'est pas initialisé correctement.", 1, msg.getID_Message());
		
		Assert.assertEquals("La date du message n'est pas initialisé correctement.", date.getYear(), msg.getDtMessage().getYear() );
		Assert.assertEquals("La date du message n'est pas initialisé correctement.", date.getMonth(), msg.getDtMessage().getMonth());
		Assert.assertEquals("La date du message n'est pas initialisé correctement.", date.getDay(), msg.getDtMessage().getDay());
		
		Assert.assertEquals("L'id_utilisateur du message n'est pas initialisé correctement.", 2, msg.getID_Utilisateur());
		Assert.assertEquals("Le contenu du message n'est pas initialisé correctement.", "content", msg.getsMessage());
	}

}

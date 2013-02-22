package com.polytech.propps.models;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import com.polytech.propps.appli.PROPPS;
import com.polytech.propps.utilsTest.Comparaison;

public class ExperienceProTest {
	private Date datedebut4;
	private Date datefin4;
	private Societe societe4;
	private Profil profil4;
	
	@BeforeClass
	public static void setUpClass() throws IOException, ClassNotFoundException {
		PROPPS.init();
		
	}
	
	@AfterClass
	public static void tearDownClass() {
		
	}
	
	@Before
	public void setUp() {
		datedebut4 = new Date(2013, 02, 19);
		datefin4 = new Date(2013, 02, 20);
		societe4 = new Societe(1);
		profil4 = new Profil(1);
	}
	
	@After
	public void tearDown() {
		// TO-DO delete xpPro1s	
	}
	
	@Test
	public void equalsTest() {
		
		ExperiencePro xpPro1 = new ExperiencePro(4);
		ExperiencePro xpPro2 = new ExperiencePro(4);
		ExperiencePro xpPro3 = new ExperiencePro(7);
		
		ExperiencePro xpPro4 = new ExperiencePro(-1);
		ExperiencePro xpPro5 = new ExperiencePro(-1);
		ExperiencePro xpPro6 = new ExperiencePro(-1);
		
		xpPro4.setDirection("Direction.");
		xpPro4.setDtDebut(datedebut4);
		xpPro4.setDtFin(datefin4);
		xpPro4.setPosteOccupe("post");
		xpPro4.setSociete(societe4);
		xpPro4.setProfil(profil4);
		
		xpPro5.setDirection("Direction.");
		xpPro5.setDtDebut(datedebut4);
		xpPro5.setDtFin(datefin4);
		xpPro5.setPosteOccupe("post");
		xpPro5.setSociete(societe4);
		xpPro5.setProfil(profil4);
		
		xpPro6.setDirection("Direction differente.");
		xpPro6.setDtDebut(datedebut4);
		xpPro6.setDtFin(datefin4);
		xpPro6.setPosteOccupe("post");
		xpPro6.setSociete(societe4);
		xpPro6.setProfil(profil4);
		
		assertTrue("xpPro1 est différent de xpPro2 alors qu'il ne devrait pas.", xpPro1.equals(xpPro2));
		assertFalse("xpPro1 est égal à xpPro3 alors qu'il ne devrait pas.", xpPro1.equals(xpPro3));
		
		assertTrue("xpPro4 est différent de xpPro5 alors qu'il ne devrait pas.", xpPro4.equals(xpPro5));
		assertFalse("xpPro4 est égal à xpPro6 alors qu'il ne devrait pas.", xpPro4.equals(xpPro6));
	}
}

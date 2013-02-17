package com.polytech.propps.models;

import java.io.IOException;

import org.junit.*;

import com.polytech.propps.appli.PROPPS;

public class MembreTest {
	private Membre membre;
	
	@BeforeClass
	public static void setUpClass() throws IOException, ClassNotFoundException {
		PROPPS.init();
	}
	
	@AfterClass
	public static void tearDownClass() {
		
	}
	
	@Before
	public void setUp() {
		//membre =  = new Membre("Clem", "Titi", "email@test.fr", "MotDePasse", new Profil(1), bContrat, bPresta, dtFinPresta);
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void insert() {
	
	}
}

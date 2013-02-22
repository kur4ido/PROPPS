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

public class ProfilTest {
	
	private Profil p1;
	private Profil p2;
	private Profil p1_bis;
	
	@BeforeClass
	public static void setUpClass() throws IOException, ClassNotFoundException {
		PROPPS.init();
		
	}
	
	@AfterClass
	public static void tearDownClass() {
		
	}
	
	@Before
	public void setUp() {
		
		p1 = new Profil(1);
		p1_bis = new Profil(1);
		p2 = new Profil(100);
		
	}
	
	@After
	public void tearDown() {
	
	}
	
	@Test
	public void InstanciationListOfProfilTest(){
		
		Assert.assertNotNull(p1.listOfProfil);
		Assert.assertFalse("La liste est vide", p1.listOfProfil.isEmpty());
		Assert.assertEquals("La liste n'a pas le bon nombre de valeurs", 3, p1.listOfProfil.size());
		
		Assert.assertEquals("La valeur associée à l'indice 1 n'est pas MOA",  "MOA", p1.listOfProfil.get(1).getNom());
		Assert.assertEquals("La valeur associée à l'indice 2 n'est pas MOE",  "MOE", p1.listOfProfil.get(2).getNom());
		Assert.assertEquals("La valeur associée à l'indice 3 n'est pas Double Compétence",  "Double Compétence", p1.listOfProfil.get(3).getNom());
		
	}
	
	@Test
	public void ConstructeurProfilTest(){
		
		Assert.assertEquals("L'id n'est pas initialisé.", 1, p1.getID());
		Assert.assertEquals("L'id n'est pas initialisé.", 100, p2.getID());
		
		Assert.assertEquals("La valeur associé à l'id connu n'a pas été récupérée.", "MOA", p1.getNom());
		Assert.assertNull("La valeur associé à l'id inconnu n'a pas été mise à NULL.", p2.getNom());
		
	}
	
	@Test
	public void FillListProfilTest(){
		
		p1.listOfProfil = null;
		
		Assert.assertNull("La listOfProfil n'a pas été forcée à Null.", p1.listOfProfil);
		
		p1.fillList();
		
		InstanciationListOfProfilTest();
		
	}
	
	public void EqualsOnProfilsTest(){
		
		Assert.assertTrue("p1 et p1_bis devraient etre égaux (ID)", p1.equals(p1_bis));
		Assert.assertFalse("p1 et p2 ne devraient pas etre égaux (ID)", p1.equals(p2));
		
	}

}

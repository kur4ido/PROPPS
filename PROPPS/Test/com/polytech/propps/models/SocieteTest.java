package com.polytech.propps.models;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.polytech.propps.appli.PROPPS;

public class SocieteTest {

	Societe s1;
	Societe s1_bis;
	Societe s2;
	
	@BeforeClass
	public static void setUpClass() throws IOException, ClassNotFoundException {
		PROPPS.init();
		
	}
	
	@AfterClass
	public static void tearDownClass() {
		
	}
	
	@Before
	public void setUp() {
		
		s1 = new Societe(1);
		s1_bis = new Societe(1);
		s2 = new Societe(100);
		
	}
	
	@After
	public void tearDown() {
	
	}
	
	@Test
	public void InstanciationListOfSocieteTest(){
		
		Assert.assertNotNull(Societe.listOfSociete);
		Assert.assertNotNull(Societe.listOfSocieteString);
		
		Assert.assertFalse("La liste est vide", Societe.listOfSociete.isEmpty());
		Assert.assertFalse("La liste de string est vide", Societe.listOfSocieteString.isEmpty());
		
		Assert.assertEquals("La liste n'a pas le bon nombre de valeurs", 6, Societe.listOfSociete.size());
		Assert.assertEquals("La liste de string n'a pas le bon nombre de valeurs", 6, Societe.listOfSocieteString.size());
		
		Assert.assertEquals("La valeur associée à l'indice 1 n'est pas Nintendo",  "Nintendo", Societe.listOfSociete.get(1).getsNom());
		Assert.assertEquals("La valeur associée à l'indice 2 n'est pas Microsoft",  "Microsoft", Societe.listOfSociete.get(2).getsNom());
		Assert.assertEquals("La valeur associée à l'indice 3 n'est pas Apple",  "Apple", Societe.listOfSociete.get(3).getsNom());
		Assert.assertEquals("La valeur associée à l'indice 4 n'est pas HSBC",  "HSBC", Societe.listOfSociete.get(4).getsNom());
		Assert.assertEquals("La valeur associée à l'indice 5 n'est pas LCL Orsay",  "LCL Orsay", Societe.listOfSociete.get(5).getsNom());
		Assert.assertEquals("La valeur associée à l'indice 6 n'est pas LCL la Sorbonne",  "LCL la Sorbonne", Societe.listOfSociete.get(6).getsNom());
		
		Assert.assertEquals("La valeur associée à Nintendo n'est pas l'indice 1",  1, Societe.listOfSocieteString.get("Nintendo").getID());
		Assert.assertEquals("La valeur associée à Microsoft n'est pas l'indice 2",  2, Societe.listOfSocieteString.get("Microsoft").getID());
		Assert.assertEquals("La valeur associée à Apple n'est pas l'indice 3",  3, Societe.listOfSocieteString.get("Apple").getID());
		Assert.assertEquals("La valeur associée à HSBC n'est pas l'indice 4",  4, Societe.listOfSocieteString.get("HSBC").getID());
		Assert.assertEquals("La valeur associée à LCL Orsay n'est pas l'indice 5", 5, Societe.listOfSocieteString.get("LCL Orsay").getID());
		Assert.assertEquals("La valeur associée à LCL la Sorbonne n'est pas l'indice 6", 6, Societe.listOfSocieteString.get("LCL la Sorbonne").getID());
		
	}
	
	@Test
	public void ConstructeurSocieteTest(){
		
		Assert.assertEquals("L'id n'est pas initialisé.", 1, s1.getID());
		Assert.assertEquals("L'id n'est pas initialisé.", 100, s2.getID());
		
		Assert.assertEquals("La valeur associé à l'id connu n'a pas été récupérée.", "Nintendo", s1.getsNom());
		Assert.assertNull("La valeur associé à l'id inconnu n'a pas été mise à NULL.", s2.getsNom());
		
	}
	
	@Test
	public void FillListSocieteTest(){
		
		Societe.listOfSociete = null;
		Societe.listOfSocieteString = null;
		
		Assert.assertNull("La listOfSociete n'a pas été forcée à Null.", Societe.listOfSociete);
		Assert.assertNull("La listOfSociete n'a pas été forcée à Null.", Societe.listOfSocieteString);
		
		Societe.fillList();
		
		InstanciationListOfSocieteTest();
		
	}
	
	public void addSociete_deleteSociete_Tests(){
		
		String name = "TEST";
		Societe scte = Societe.addSociete(name);
		
		Assert.assertEquals("Le nom de la societe crée n'est pas bon", name,  scte.getsNom());
		Assert.assertEquals("L'ID de la société crée n'est celui attendu (7 pour ce test)", 7, scte.getID());
		
		Societe.deleteSociete(scte.getID());
		
		Societe.fillList();
		
		Assert.assertEquals("La suppression n'a pas été effective.", 6, Societe.listOfSociete);
		Assert.assertEquals("La suppression n'a pas été effective.", 6, Societe.listOfSocieteString);
		
	}
	
	

	
}


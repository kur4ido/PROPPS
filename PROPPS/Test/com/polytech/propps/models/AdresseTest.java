package com.polytech.propps.models;



import java.io.IOException;


import org.junit.*;


public class AdresseTest {
	private Adresse adr;
	
	@BeforeClass
	public static void setUpClass() throws IOException, ClassNotFoundException {
		//PROPPS.init();
		
	}
	
	@AfterClass
	public static void tearDownClass() {
		
	}
	
	@Before
	public void setUp() {
		adr = new Adresse("Orsay", "91400", "69 rue des pingouins", "France");
	}
	
	@After
	public void tearDown() {
		
	}
	
	/*protected String sVille;
	protected String sCodePostal;
	protected String sAdresse;
	protected String sPays;*/
	
	@Test
	public void ConstructeurAdresseTest(){
		
		Assert.assertEquals("La ville a été mal intialisée.", "Orsay", adr.getVille());
		Assert.assertEquals("Le code postal a été mal intialisé.", "91400", adr.getCodePostal());
		Assert.assertEquals("L'adresse a été mal intialisée.", "69 rue des pingouins", adr.getAdresse());
		Assert.assertEquals("Le pays a été mal intialisé.", "France", adr.getPays());
		
		
	}
	
	@Test
	public void gettersTest(){
		Assert.assertEquals("La méthode getVille ne retourne pas correctement.", "Orsay", adr.getVille());
		Assert.assertEquals("La méthode getCodePostal ne retourne pas correctement.", "91400", adr.getCodePostal());
		Assert.assertEquals("La méthode getAdresse ne retourne pas correctement.", "69 rue des pingouins", adr.getAdresse());
		Assert.assertEquals("La méthode getPays ne retourne pas correctement.", "France", adr.getPays());
	}
	
	@Test
	public void settersTest(){
		adr.setVille("Paris");
		Assert.assertEquals("La méthode getVille ne retourne pas correctement.", "Paris", adr.getVille());
		
		adr.setCodePostal("75011");
		Assert.assertEquals("La méthode getCodePostal ne retourne pas correctement.", "75011", adr.getCodePostal());
		
		adr.setAdresse("3 rue de la soif");
		Assert.assertEquals("La méthode getAdresse ne retourne pas correctement.", "3 rue de la soif", adr.getAdresse());
		
		adr.setPays("Chine");
		Assert.assertEquals("La méthode getPays ne retourne pas correctement.", "Chine", adr.getPays());
	}
	
}

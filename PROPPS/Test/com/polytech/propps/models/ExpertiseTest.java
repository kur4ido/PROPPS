package com.polytech.propps.models;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.polytech.propps.appli.PROPPS;

public class ExpertiseTest {

	
	@BeforeClass
	public static void setUpClass() throws IOException, ClassNotFoundException {
		
		PROPPS.init();
	}
	
	@AfterClass
	public static void tearDownClass() {
		
	}
	
	@Before
	public void setUp() {
		
	}
	
	@After
	public void tearDown() {
		// TO-DO delete xpPro1s	
	}
	
	@Test
	public void equalsTest() {
		
		Expertise e1 = new Expertise(1);
		Expertise e1_bis = new Expertise(1);
		Expertise e2 = new Expertise(100);
		
		
		Assert.assertTrue("e1 et e1_bis devraient etre egaux", e1.equals(e1_bis));
		Assert.assertFalse("e1 et e2 devraient etre egaux", e1.equals(e2));
		
	}
}

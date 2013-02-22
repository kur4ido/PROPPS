package com.polytech.propps.testingCenter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.polytech.propps.models.AdresseTest;
import com.polytech.propps.models.MembreTest;
import com.polytech.propps.models.MessageTest;
import com.polytech.propps.models.NotificationTest;
import com.polytech.propps.models.ProfilTest;
import com.polytech.propps.models.RecruteurTest;
import com.polytech.propps.models.SocieteTest;
import com.polytech.propps.models.UtilisateurTest;

@RunWith(Suite.class)
@SuiteClasses({ MembreTest.class, RecruteurTest.class, AdresseTest.class, MessageTest.class, NotificationTest.class, ProfilTest.class, SocieteTest.class, UtilisateurTest.class})
public class UnitTestCenter {

	
	
}
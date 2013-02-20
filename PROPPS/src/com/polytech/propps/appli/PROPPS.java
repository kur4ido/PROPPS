package com.polytech.propps.appli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.polytech.propps.bdd.Base;
import com.polytech.propps.models.Expertise;
import com.polytech.propps.models.Profil;
import com.polytech.propps.models.Recruteur;

public class PROPPS {
	private static final String fileConfig = "WebContent" + File.separator + "propps.conf";
	private static final String TOKEN_COMMENTAIRE = ";";
	private static final String TOKEN_SPEC = "-";
	private static final String TOKEN_VALUE = ":";
	
	private static String MYSQL = "mysql";
	private static String MYSQL_USR_APPLI = "usr";
	private static String MYSQL_PW_APPLI = "pw";
	private static String MYSQL_URL = "url";
	
	public static void init() throws IOException, ClassNotFoundException {
		BufferedReader buff = new BufferedReader(new FileReader(fileConfig));
		try {
			String line;
			while((line = buff.readLine()) != null) {
				if(!(line.startsWith(TOKEN_COMMENTAIRE)) && !line.isEmpty()) {
					traiterLigne(line);
				}
			}
		}finally {
			buff.close();
		}
		Base.initBase();
		Profil.fillList();
		Expertise.fillList();
	}
	
	private static void traiterLigne(String line) {
		if(line.startsWith(MYSQL)) {
			initSQL(line.substring(line.indexOf(TOKEN_SPEC) + 1));
		}
		
	}
	private static void initSQL(String line) {
		if(line.startsWith(MYSQL_URL)) {
			Base.setURL(line.substring(line.indexOf(TOKEN_VALUE) + 1));
		}else if(line.startsWith(MYSQL_USR_APPLI)) {
			Base.setUser(line.substring(line.indexOf(TOKEN_VALUE) + 1));
		}else if(line.startsWith(MYSQL_PW_APPLI)) {
			Base.setPassWord(line.substring(line.indexOf(TOKEN_VALUE) + 1));
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

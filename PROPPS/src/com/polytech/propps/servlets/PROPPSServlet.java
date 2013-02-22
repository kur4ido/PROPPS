package com.polytech.propps.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.bdd.Base;
import com.polytech.propps.models.Expertise;
import com.polytech.propps.models.Profil;
import com.polytech.propps.models.Recruteur;
import com.polytech.propps.models.Societe;

@WebServlet("/PROPPSServlet")
public class PROPPSServlet implements ServletContextListener {
	private static final String fileConfig = "propps.conf";
	private static final String TOKEN_COMMENTAIRE = ";";
	private static final String TOKEN_SPEC = "-";
	private static final String TOKEN_VALUE = ":";
	
	private static String MYSQL = "mysql";
	private static String MYSQL_USR_APPLI = "usr";
	private static String MYSQL_PW_APPLI = "pw";
	private static String MYSQL_URL = "url";
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContextListener started");
        BufferedReader buff;
        InputStream ips;
		try {
			ips = new FileInputStream(arg0.getServletContext().getRealPath(fileConfig));
			InputStreamReader ipsr=new InputStreamReader(ips);
			buff = new BufferedReader(ipsr);
			String line;
			while((line = buff.readLine()) != null) {
				if(!(line.startsWith(TOKEN_COMMENTAIRE)) && !line.isEmpty()) {
					traiterLigne(line);
				}
			}
			buff.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Base.initBase();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Profil.fillList();
		Expertise.fillList();
		Societe.fillList();
	}
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
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

}

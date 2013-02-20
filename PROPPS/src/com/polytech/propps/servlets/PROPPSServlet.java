package com.polytech.propps.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.bdd.Base;
import com.polytech.propps.models.Expertise;
import com.polytech.propps.models.Profil;
import com.polytech.propps.models.Recruteur;

@WebServlet("/PROPPSServlet")
public class PROPPSServlet extends HttpServlet {
	private static final String fileConfig = "propps.conf";
	private static final String TOKEN_COMMENTAIRE = ";";
	private static final String TOKEN_SPEC = "-";
	private static final String TOKEN_VALUE = ":";
	
	private static String MYSQL = "mysql";
	private static String MYSQL_USR_APPLI = "usr";
	private static String MYSQL_PW_APPLI = "pw";
	private static String MYSQL_URL = "url";
	
	public void doGet(HttpServletRequest request, 
		      		  HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getContextPath());
		BufferedReader buff = new BufferedReader(new FileReader(request.getContextPath() + File.separator+fileConfig));
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
		try {
			Base.initBase();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Profil.fillList();
		Expertise.fillList();
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.html"));
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

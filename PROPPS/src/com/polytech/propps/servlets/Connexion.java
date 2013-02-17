package com.polytech.propps.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.bdd.Base;
import com.polytech.propps.models.Membre;
import com.polytech.propps.models.Recruteur;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/Connexion")
public class Connexion extends HttpServlet {

	/**
	 *  connexion classe 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet( HttpServletRequest request, HttpServletResponse
			response ) throws ServletException, IOException{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("EMAIL : "+email+"\tPASSWORD : "+password);
		Base base = new Base();
		try {
			Base.initBase("jdbc:mysql://localhost:8889/PROPPS_DB", "propps#BDD!", "#aVjbBfTmJcT#");
			base.connect();
			
			base.procedureInit("Membre_getIDByLoginPW", 2);
			base.setParamString("_sEmail", email);
			base.setParamString("_sPassword", password);
			ResultSet result = base.executeQuery();
			if(result.next()){
				Membre membre = new Membre(result.getInt("ID_Utilisateur"));
				membre.fill();
				
			}else{
				base.procedureInit("Recruteur_getIDByLoginPW", 2);
				base.setParamString("_sEmail", email);
				base.setParamString("_sPassword", password);
				result = base.executeQuery();
				if(result.next()){
					Recruteur recruteur = new Recruteur(result.getInt("ID_Utilisateur"));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			base.close();
		}
	}

}


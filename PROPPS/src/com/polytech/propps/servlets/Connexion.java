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

	public void doPost( HttpServletRequest request, HttpServletResponse
			response ) throws ServletException, IOException{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("EMAIL : "+email+"\tPASSWORD : "+password);
		Base base = new Base();
		try {
			base.connect();
			
			base.procedureInit("Membre_getIDByLoginPW", 2);
			base.setParamString("_sEmail", email);
			base.setParamString("_sPassword", password);
			ResultSet result = base.executeQuery();
			if(result.next()){
				System.out.println("Il existe un membre avec cette adresse email : "+result.getInt("ID_Utilisateur"));
				Membre membre = new Membre(result.getInt("ID_Utilisateur"));
				membre.fill();
				request.setAttribute("nom", membre.getsNom());
				request.setAttribute("prenom", membre.getsPrenom());
				request.removeAttribute("password");
				request.removeAttribute("email");
				System.out.println(membre.getAdresse().getVille());
				request.setAttribute("ville", membre.getAdresse().getVille());
				getServletContext().getRequestDispatcher("/jsp/compte.jsp").forward(request, response);
				
			}else{
				base.procedureInit("Recruteur_getIDByLoginPW", 2);
				base.setParamString("_sEmail", email);
				base.setParamString("_sPassword", password);
				result = base.executeQuery();
				if(result.next()){
					Recruteur recruteur = new Recruteur(result.getInt("ID_Utilisateur"));
				}else{
					String error = "Mot de passe ou login incorrect";
					request.setAttribute("error", error);
					request.setAttribute("email", email);
					getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(request, response);					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			base.close();
		}
	}

}


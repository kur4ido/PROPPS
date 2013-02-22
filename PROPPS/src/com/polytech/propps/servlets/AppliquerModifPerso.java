package com.polytech.propps.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.models.Adresse;
import com.polytech.propps.models.Membre;

@WebServlet("/AppliquerModifPerso")
public class AppliquerModifPerso extends HttpServlet{
	
	public void doPost( HttpServletRequest request, HttpServletResponse	response ) throws ServletException, IOException{
		int ID_Membre_Courant = Integer.parseInt(request.getParameter(ParametresServlet.ID_Membre_Courant));
		String name = request.getParameter(ParametresServlet.Nom);
		String prenom = request.getParameter(ParametresServlet.Prenom);
		String ville = request.getParameter(ParametresServlet.Ville);
		
		Membre membre = new Membre(ID_Membre_Courant);
		membre.fill();
		membre.setsNom(name);
		membre.setsPrenom(prenom);
		membre.setAdresse(new Adresse(ville, null, null, null));
		
		membre.insertOrUpdate();
		request.setAttribute(ParametresServlet.ID_Membre_Courant, Integer.toString(ID_Membre_Courant));
		getServletContext().getRequestDispatcher("/seeCurrentUserProfile").forward(request, response);
	}
	

	public void doGet( HttpServletRequest request, HttpServletResponse	response ) throws ServletException, IOException{
		doPost(request, response);
	}
}

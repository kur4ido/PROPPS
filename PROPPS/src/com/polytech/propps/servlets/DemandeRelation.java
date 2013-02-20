package com.polytech.propps.servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.bdd.Base;
import com.polytech.propps.models.Membre;
import com.polytech.propps.models.Recruteur;

@WebServlet("/DemandeRelation")
public class DemandeRelation extends HttpServlet {
	
	public void doPost( HttpServletRequest request, HttpServletResponse
			response ) throws ServletException, IOException{
		int ID_Membre = Integer.parseInt(request.getParameter(ParametresServlet.ID_Membre_Courant));
		int ID_Destinataire = Integer.parseInt(request.getParameter(ParametresServlet.ID_Membre_Select));
		Membre current = new Membre(ID_Membre);
		current.demanderContact(new Membre(ID_Destinataire));
		
	}
}

package com.polytech.propps.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.models.Membre;

@WebServlet("/ReponseDemande")
public class ReponseDemande extends HttpServlet{
	
	public void doPost( HttpServletRequest request, HttpServletResponse
			response ) throws ServletException, IOException{
		int ID_Membre = Integer.parseInt(request.getParameter(ParametresServlet.ID_Membre_Courant));
		int ID_Destinataire = Integer.parseInt(request.getParameter(ParametresServlet.ID_Membre_Select));
		Membre current = new Membre(ID_Membre);
		if(Boolean.parseBoolean(request.getParameter(ParametresServlet.demandeAccepte))) {
			current.addContact(new Membre(ID_Destinataire));
			//TODO Messagerie
			
		}else {
			
		}
		
	}
}

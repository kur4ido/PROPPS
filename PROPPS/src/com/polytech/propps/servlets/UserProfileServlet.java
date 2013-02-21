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

@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {

	/**
	 *  connexion classe 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost( HttpServletRequest request, HttpServletResponse	response ) throws ServletException, IOException{
		int ID_Membre_Courant = Integer.parseInt(request.getParameter(ParametresServlet.ID_Membre_Courant));
		int ID_Membre_Demande = Integer.parseInt(request.getParameter(ParametresServlet.ID_Membre_Select));
		System.out.println("ID_Membre_Courant : "+ID_Membre_Courant+"\tID_Membre_Demande : "+ID_Membre_Demande);
		Base base = new Base();
		try {
			base.connect();
			Membre membreCourant = new Membre(ID_Membre_Courant);
			String prenomCourant = membreCourant.getsPrenom();
			String nomCourant = membreCourant.getsNom();
			
			Membre membreDemande = new Membre(ID_Membre_Demande);
			String prenomDemande = membreDemande.getsPrenom();
			String nomDemande = membreDemande.getsNom();
			String villeDemande = membreDemande.getAdresse().getVille();
			
			System.out.println("Prenom : "+prenomDemande+"\tNom : "+nomDemande+"\tVille : "+villeDemande);
			
			request.setAttribute("membreSelect", membreDemande);
			request.setAttribute(ParametresServlet.ville_Membre_Select, villeDemande);
			
			request.setAttribute("membreCourant", membreCourant);
			
			String isContact;
			if(membreCourant.aCommeAmi(membreDemande)){
				isContact = "amis";
			}else if(membreCourant.aEnvoyeInvit(membreDemande)){
				isContact = "attenteConfirm";
			}else if(membreCourant.aEteInvitePar(membreDemande)){
				isContact = "OuiNon";
			}else{
				isContact = "nonAmis";
			}
			System.out.println(isContact);
			request.setAttribute("isContact", isContact);
			getServletContext().getRequestDispatcher("/jsp/user.jsp").forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			base.close();
		}
	}

}


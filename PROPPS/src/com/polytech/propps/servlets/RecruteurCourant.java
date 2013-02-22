package com.polytech.propps.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.models.Expertise;
import com.polytech.propps.models.Profil;
import com.polytech.propps.models.Recruteur;

/**
 * Servlet implementation class RecruteurCourant
 */
@WebServlet("/RecruteurCourant")
public class RecruteurCourant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ID_Membre_Courant = Integer.parseInt(request.getParameter(ParametresServlet.ID_Membre_Courant));
		Recruteur recruteur = new Recruteur(ID_Membre_Courant);
		recruteur.fill();
		 request.setAttribute("nom", recruteur.getsNom());
		 request.setAttribute("prenom", recruteur.getsPrenom());
		 request.setAttribute("ville", recruteur.getAdresse().getVille());
		 request.setAttribute("adresse", recruteur.getAdresse().getAdresse());
		 request.setAttribute("codePostal", recruteur.getAdresse().getCodePostal());
		 request.setAttribute(ParametresServlet.NomSociete, recruteur.getSociete().getsNom());
		 request.setAttribute(ParametresServlet.ID_Membre_Courant,Integer.toString(recruteur.getID_Utilisateur()));
		 
		 HashMap<Integer,Profil> listProfils = Profil.getListOfProfil();
		HashMap<Integer,String> listStringProfils = new HashMap<Integer,String>();
		for(int i : listProfils.keySet()){
			listStringProfils.put(i, listProfils.get(i).getsNom());
		}
		
		request.setAttribute("mapProfils", listStringProfils);
		
		HashMap<Integer,Expertise> listExpertises = Expertise.getListOfExpertise();
		HashMap<Integer,String> listStringExpertises = new HashMap<Integer,String>();
		for(int i : listExpertises.keySet()){
			listStringExpertises.put(i, listExpertises.get(i).getsDomaine()+" - "+listExpertises.get(i).getsType());
		}
		
		request.setAttribute("mapExpertises", listStringExpertises);
		 
		getServletContext().getRequestDispatcher("/jsp/entreprise.jsp").forward(request, response);
	}

}

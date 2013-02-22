package com.polytech.propps.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.bdd.Base;
import com.polytech.propps.models.Adresse;
import com.polytech.propps.models.Membre;
import com.polytech.propps.models.Notification;
import com.polytech.propps.models.Utilisateur;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@WebServlet("/ModifInfosPersoServlet")
public class ModifInfosPersoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost( HttpServletRequest request, HttpServletResponse
			response ) throws ServletException, IOException{
		int ID_Membre_Courant = Integer.parseInt(request.getParameter(ParametresServlet.ID_Membre_Courant));
		Base base = new Base();
		try {
			base.connect();
			Membre membre = new Membre(ID_Membre_Courant);
			membre.fill();
			request.setAttribute("nom", membre.getsNom());
			request.setAttribute("prenom", membre.getsPrenom());
			request.setAttribute("ville", membre.getAdresse().getVille());	
			request.setAttribute("email", membre.getsEmail());
			request.setAttribute(ParametresServlet.ID_Membre_Courant, Integer.toString(ID_Membre_Courant));
			System.out.println(membre.getAdresse().getVille());

			HashMap<Integer,Notification> lstNotifRecept = membre.getLstNotifRecept();
			System.out.println(lstNotifRecept.toString());
			HashMap<String,Integer> mapNotifRecept = new HashMap<String,Integer>();
			for(Notification n : lstNotifRecept.values()){
				Membre tmp = n.getSource();
				System.out.println(tmp.getsPrenom() + " "+ tmp.getsNom());
				mapNotifRecept.put(tmp.getsPrenom() + " "+ tmp.getsNom(), n.getID());
			}
			request.setAttribute("mapNotifRecept", mapNotifRecept);
			request.setAttribute("nbNotif", Integer.toString(lstNotifRecept.size()));
			request.setAttribute("lstNotifRecept", lstNotifRecept);
			
			request.setAttribute("errorMdpInvalide", "");
			request.setAttribute("errorMail", "");
			request.setAttribute("errorMdp", "");
			request.setAttribute("errorOldMdp", "");
			
			getServletContext().getRequestDispatcher("/jsp/parametre.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			base.close();
		}
	}
	
	public void doGet( HttpServletRequest request, HttpServletResponse	response ) throws ServletException, IOException{
		doPost(request, response);
	}

}


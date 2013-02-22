package com.polytech.propps.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.models.Membre;
import com.polytech.propps.models.Message;
import com.polytech.propps.models.Recruteur;

/**
 * Servlet implementation class RecupMessages
 */
@WebServlet("/RecupMessages")
public class RecupMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		int ID_Membre_Courant = Integer.parseInt(request.getParameter(ParametresServlet.ID_Membre_Courant));
		Recruteur recruteur = new Recruteur(ID_Membre_Courant);
		recruteur.fillMessages();
		Membre membre = new Membre(ID_Membre_Courant);
		membre.fillMessages();
		ArrayList<Message> messages = recruteur.getlstMessage();
		ArrayList<Message> messagesM = recruteur.getlstMessage();
		HashMap<String, Date> messString = new HashMap<String, Date>();
		for(Message m : messages){
			//String tmp = m.getsMessage() +" à "+m.getDtMessage().toString();
			messString.put(m.getsMessage(), m.getDtMessage());
		}
		for(Message m : messagesM){
			//String tmp = m.getsMessage() +" à "+m.getDtMessage().toString();
			messString.put(m.getsMessage(), m.getDtMessage());
		}
		request.setAttribute("messString", messString);
		if(recruteur.getSociete() != null)
			request.setAttribute("societe", recruteur.getSociete().getsNom());
		else
			request.setAttribute("societe", null);
		request.setAttribute("nom", membre.getsNom());
		request.setAttribute("prenom", membre.getsPrenom());
		request.setAttribute(ParametresServlet.ID_Membre_Courant, Integer.toString(ID_Membre_Courant));
		getServletContext().getRequestDispatcher("/jsp/messagerie.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

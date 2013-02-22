package com.polytech.propps.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.models.Membre;
import com.polytech.propps.models.Message;

/**
 * Servlet implementation class recupEmail
 */
@WebServlet("/recupEmail")
public class recupEmail extends HttpServlet {
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
		int ID_Membre_Select = Integer.parseInt(request.getParameter(ParametresServlet.ID_Membre_Select));
		Membre m = new Membre(ID_Membre_Select);
		String messageString = "L'adresse mail de "+m.getsPrenom()+" "+m.getsNom()+" est "+m.getsEmail();

		java.sql.Date sqlToday;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date date = new java.util.Date();
		String d = dateFormat.format( date );
		sqlToday = java.sql.Date.valueOf(d);
		
		Message mess = new Message(-1, messageString, ID_Membre_Courant, sqlToday);

		request.setAttribute(ParametresServlet.ID_Membre_Courant, ID_Membre_Courant);
		request.setAttribute(ParametresServlet.ID_Membre_Select, ID_Membre_Select);
		getServletContext().getRequestDispatcher("/seeUserProfile").forward(request, response);
	}

}

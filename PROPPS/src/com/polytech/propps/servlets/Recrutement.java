package com.polytech.propps.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.models.Expertise;
import com.polytech.propps.models.Membre;
import com.polytech.propps.models.Profil;
import com.polytech.propps.models.Recruteur;

/**
 * Servlet implementation class Recrutement
 */
@WebServlet("/Recrutement")
public class Recrutement extends HttpServlet {
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
		int idProfil = Integer.parseInt(request.getParameter("profil"));
		String[] res = request.getParameterValues("expertise");
		String dateDebut = request.getParameter("dateDebut");
		String dateFin = request.getParameter("dateFin");
		int experience = Integer.parseInt(request.getParameter("experience"));
		boolean mode = Boolean.parseBoolean(request.getParameter("mode"));

		java.sql.Date sqlDateDebut;
		try {
			java.util.Date parsedDate;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			parsedDate = dateFormat.parse(dateDebut);
			dateDebut = dateFormat.format(parsedDate);
			System.out.println(dateDebut);
			sqlDateDebut = java.sql.Date.valueOf(dateDebut);
			
			Recruteur recruteurCourant = new Recruteur(ID_Membre_Courant);
			recruteurCourant.fill();
			ArrayList<Expertise> lstExpertise = new ArrayList<Expertise>();
			for(int i =0; i< res.length; i++){
				lstExpertise.add(new Expertise(Integer.parseInt(res[i])));
			}
			Profil p = new Profil(idProfil);
			ArrayList<Membre> result = recruteurCourant.rechercheAvancee(lstExpertise, p, mode, sqlDateDebut, experience);
			
			for(Membre m : result){
				System.out.println("ok");
			}
			request.setAttribute(ParametresServlet.ID_Membre_Courant, ID_Membre_Courant);
			getServletContext().getRequestDispatcher("/currentRecruiter").forward(request, response);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

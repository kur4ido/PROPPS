package com.polytech.propps.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.bdd.Base;
import com.polytech.propps.models.Profil;
import com.polytech.propps.models.Societe;

/**
 * Servlet implementation class AddExperiencePro
 */
@WebServlet("/AddExperiencePro")
public class AddExperiencePro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nomSociete = request.getParameter("societe");
		String typeProfil = request.getParameter("profil");
		String dispo = request.getParameter("dispo"); // INUTILE?
		String direction = request.getParameter("direction");
		String poste = request.getParameter("poste");
		String description = request.getParameter("description");
		//On récupére l'ensemble des domaines d'expertise
		String[] res = request.getParameterValues("expertise");
		for (int i = 0; i < res.length; ++i) {
			System.out.println(res[i]);
		}
		String dateDebut = request.getParameter("dateDebut");
		String dateFin = request.getParameter("dateFin");
		java.sql.Date sqlDateFin;
		java.sql.Date sqlDateDebut;

		Base base = new Base();

		if (!dateDebut.contentEquals("")) {
			try {
				base.connect();

				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date parsedDate;
				parsedDate = dateFormat.parse(dateDebut);
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dateDebut = dateFormat.format(parsedDate);
				System.out.println(dateDebut);
				sqlDateDebut = java.sql.Date.valueOf(dateDebut);
				if (dateFin.contentEquals("")) {
					sqlDateFin = null;
				} else {
					dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date parsedDateFin;
					parsedDateFin = dateFormat.parse(dateFin);
					dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					dateFin = dateFormat.format(parsedDateFin);
					System.out.println(dateFin);
					sqlDateFin = java.sql.Date.valueOf(dateFin);
				}
				Societe societeAjoutee = new Societe(nomSociete); // ID SOCIETE
																	// AJOUTEE
				Profil profilAjoute = new Profil(-1); // ID PROFIL AJOUTE

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				base.close();
			}
		} else {
			String erreurDateDebut = "Veuillez renseigner une date de debut";
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}

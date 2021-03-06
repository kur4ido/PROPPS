package com.polytech.propps.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.bdd.Base;
import com.polytech.propps.models.ExperiencePro;
import com.polytech.propps.models.Expertise;
import com.polytech.propps.models.Membre;
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
		int ID_Membre_Courant = Integer.parseInt(request.getParameter(ParametresServlet.ID_Membre_Courant));
		int NumExpPro_Courante = Integer.parseInt(request.getParameter("NumExpPro_Courante"));
		String nomSociete = request.getParameter("societe");
		System.out.println(nomSociete);
		int idProfil = Integer.parseInt(request.getParameter("profil"));
		boolean dispo = Boolean.parseBoolean(request.getParameter("dispo")); // INUTILE?
		String direction = request.getParameter("direction");
		String poste = request.getParameter("poste");
		String description = request.getParameter("description");
		//On r�cup�re l'ensemble des domaines d'expertise
		String[] res = request.getParameterValues("expertise");
		String dateDebut = request.getParameter("dateDebut");
		String dateFin = request.getParameter("dateFin");
		java.sql.Date sqlDateFin;
		java.sql.Date sqlDateDebut;
		java.sql.Date sqlToday;

		Base base = new Base();

		if (!dateDebut.contentEquals("")) {
			try {
				base.connect();

//				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				java.util.Date parsedDate;
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				parsedDate = dateFormat.parse(dateDebut);
				dateDebut = dateFormat.format(parsedDate);
				
				java.util.Date date = new java.util.Date();
				String d = dateFormat.format( date );
				sqlToday = java.sql.Date.valueOf(d);
				System.out.println(dateDebut);
				sqlDateDebut = java.sql.Date.valueOf(dateDebut);
				if (dateFin.contentEquals("")) {
					sqlDateFin = null;
				} else {
//					dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					java.util.Date parsedDateFin;
					dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					parsedDateFin = dateFormat.parse(dateFin);
					dateFin = dateFormat.format(parsedDateFin);
					System.out.println(dateFin);
					sqlDateFin = java.sql.Date.valueOf(dateFin);
				}
				Societe societeAjoutee = Societe.addSociete(nomSociete); // ID SOCIETE
																	// AJOUTEE
				Profil profilAjoute = new Profil(idProfil); // ID PROFIL AJOUTE
				ExperiencePro newExpPro = new ExperiencePro(-1);
				
				for (int i = 0; i < res.length; ++i) {
					System.out.println(res[i]);
					newExpPro.addExpertise(new Expertise(Integer.parseInt(res[i])));
				}
				newExpPro.setDescription(description);
				newExpPro.setDtDebut(sqlDateDebut);
				newExpPro.setDtFin(sqlDateFin);
				newExpPro.setPosteOccupe(poste);
				newExpPro.setProfil(profilAjoute);
				newExpPro.setSociete(societeAjoutee);
				newExpPro.setDirection(direction);
				
				Membre membre = new Membre(ID_Membre_Courant);
				membre.fill();
				if (dateFin.contentEquals("") || (sqlDateFin.after(sqlToday) && sqlDateDebut.before(sqlToday)))
					membre.setHasContrat(true);
				if(NumExpPro_Courante != -1){
					membre.removeExperiencePro(NumExpPro_Courante-1);
				}
				membre.addExperiencePro(newExpPro);
				membre.insertOrUpdate();
				request.setAttribute(ParametresServlet.ID_Membre_Courant, ID_Membre_Courant);
				getServletContext().getRequestDispatcher("/seeCurrentUserProfile").forward(request, response);

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

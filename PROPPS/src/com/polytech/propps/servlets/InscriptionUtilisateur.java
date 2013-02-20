package com.polytech.propps.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.bdd.Base;
import com.polytech.propps.models.Adresse;
import com.polytech.propps.models.Membre;
import com.polytech.propps.models.Utilisateur;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/InscriptionUtilisateur")
public class InscriptionUtilisateur extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost( HttpServletRequest request, HttpServletResponse
			response ) throws ServletException, IOException{
		Base base = new Base();
		try {
			base.initBase("jdbc:mysql://localhost:8889/PROPPS_DB", "propps#BDD!", "#aVjbBfTmJcT#");
			base.connect();
			
			String name = request.getParameter(ParametresServlet.Nom);
			String prenom = request.getParameter(ParametresServlet.Prenom);
			String email = request.getParameter(ParametresServlet.Email);
			String mdp = request.getParameter(ParametresServlet.MotDePasse);
			String confirmMdp = request.getParameter(ParametresServlet.ConfirmerMotDePasse);
			String ville = request.getParameter(ParametresServlet.Ville);
			System.out.println("Nom : "+name+"\tPrenom : "+prenom+"\tMail : "+email);
			System.out.println("Mdp : "+mdp+"\tConfirmMdp : "+confirmMdp+"\tVille : "+ville);
			if(mdp.matches(".*[A-Z].*") && mdp.matches(".*[0-9].*") && mdp.matches(".*[a-z].*") && mdp.length()>=8){
				
				if(mdp.contentEquals(confirmMdp)){
					
					System.out.println("Les deux mots de passe sont identiques");
					base.procedureInit("Utilisateur_getUtilisateurByEmail", 1);
					base.setParamString("_sEmail", email);
					ResultSet testEmailAlreadyExists = base.executeQuery();
					boolean EmailAlreadyExists = testEmailAlreadyExists.first();
					if(!EmailAlreadyExists){
						System.out.println("Le mail est disponible");
						Membre newUser = new Membre(name, prenom, email, mdp, null, false, false, null);
						newUser.setAdresse(new Adresse(ville, null, null, null));
						System.out.println(newUser.getAdresse().getVille());
						newUser.insertOrUpdate();
						request.setAttribute(ParametresServlet.Email, email);
						request.setAttribute(ParametresServlet.Nom, name);
						request.setAttribute(ParametresServlet.Prenom, prenom);
						request.setAttribute(ParametresServlet.Ville, ville);
						request.setAttribute(ParametresServlet.ID_Membre_Courant, Integer.toString(newUser.getID_Utilisateur()));
						request.setAttribute(ParametresServlet.MotDePasse, null);
						request.setAttribute(ParametresServlet.ConfirmerMotDePasse, null);
						request.removeAttribute(ParametresServlet.MotDePasse);
						request.removeAttribute(ParametresServlet.ConfirmerMotDePasse);
						getServletContext().getRequestDispatcher("/jsp/compte.jsp").forward(request, response);
	//					String attributes = "?nom="+name+"&prenom="+prenom+"&ville="+ville;
	//					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/jsp/compte.jsp"));
					}else{
						request.setAttribute("email", email);
						request.setAttribute("nom", name);
						request.setAttribute("prenom", prenom);
						request.setAttribute("ville", ville);
						String error = "Cette adresse mail est deja utilisee";
						request.setAttribute("errorMdpInvalide", "");
						request.setAttribute("errorMail", error);
						request.setAttribute("errorMdp", "");
						getServletContext().getRequestDispatcher("/jsp/inscription1.jsp").forward(request, response);
					}
				}
				else{
					String error = "Les deux mots de passe tapes sont differents";
					request.setAttribute("email", email);
					request.setAttribute("nom", name);
					request.setAttribute("prenom", prenom);
					request.setAttribute("ville", ville);
					request.setAttribute("errorMdp", error);
					request.setAttribute("errorMdpInvalide", "");
					request.setAttribute("errorMail", "");
					getServletContext().getRequestDispatcher("/jsp/inscription1.jsp").forward(request, response);
				}
			}else{
				String error = "Le mot de passe n'est pas valide";
				request.setAttribute("email", email);
				request.setAttribute("nom", name);
				request.setAttribute("prenom", prenom);
				request.setAttribute("ville", ville);
				request.setAttribute("errorMdpInvalide", error);
				request.setAttribute("errorMdp", "");
				request.setAttribute("errorMail", "");
				getServletContext().getRequestDispatcher("/jsp/inscription1.jsp").forward(request, response);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			base.close();
		}
	}

}


package com.polytech.propps.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.bdd.Base;

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

	public void doGet( HttpServletRequest request, HttpServletResponse
			response ) throws ServletException, IOException{
		Base base = new Base();
		try {
			Base.initBase("jdbc:mysql://localhost:8889/PROPPS_DB", "propps#BDD!", "#aVjbBfTmJcT#");
			base.connect();
			
			String name = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String mdp = request.getParameter("password");
			String confirmMdp = request.getParameter("confirmPassword");
			String ville = request.getParameter("ville");
			System.out.println("Nom : "+name+"\tPrenom : "+prenom+"\tMail : "+email);
			System.out.println("Mdp : "+mdp+"\tConfirmMdp : "+confirmMdp+"\tVille : "+ville);
			
			if(mdp.contentEquals(confirmMdp)){
				System.out.println("Les deux mots de passe sont identiques");
				base.procedureInit("Utilisateur_getUtilisateurByEmail", 1);
				base.setParamString("_sEmail", email);
				ResultSet testEmailAlreadyExists = base.executeQuery();
				boolean EmailAlreadyExists = testEmailAlreadyExists.first();
				if(!EmailAlreadyExists){
					System.out.println("Le mail est disponible");
					base.procedureInit("Utilisateur_ajouter", 4);
					base.setParamString("_sNom", name);
					base.setParamString("_sPrenom", prenom);
					base.setParamString("_sEmail", email);
					base.setParamString("_sPassWord", mdp);
					int id = 0;
					ResultSet result = base.executeQuery();
					while(result.next()){
						id = result.getInt("ID_Utilisateur");
					}
					base.procedureInit("Utilisateur_modifierAdresse", 5);
					base.setParamInt("_ID_Utilisateur",id);
					base.setParamString("_sVille",ville);
					base.setParamString("_sCodePostal",null);
					base.setParamString("_sPays",null);
					base.setParamString("_sAdresse",null);
					base.execute();
					base.procedureInit("Membre_ajouter", 1);
					base.setParamInt("_ID_Utilisateur", id);
					base.execute();
					request.setAttribute("email", email);
					request.setAttribute("nom", name);
					request.setAttribute("prenom", prenom);
					request.setAttribute("ville", ville);
					//response.setContentType("text/html");
					//response.encodeRedirectURL("file://../WebContent/pages/compte.html");
					getServletContext().getRequestDispatcher("/jsp/compte.jsp").forward(request, response);
				}else{
					throw new FormValidationException("Le mail est deja utilise");					
				}
			}
			else{
				throw new FormValidationException("Veuillez resaisir votre mot de passe.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			base.close();
		}
	}

}


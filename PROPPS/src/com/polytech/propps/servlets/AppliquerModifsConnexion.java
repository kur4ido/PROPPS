package com.polytech.propps.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.bdd.Base;
import com.polytech.propps.models.Adresse;
import com.polytech.propps.models.Membre;
import com.polytech.propps.models.Notification;



@WebServlet("/AppliquerModifsConnexion")
public class AppliquerModifsConnexion extends HttpServlet {

	public void doPost( HttpServletRequest request, HttpServletResponse	response ) throws ServletException, IOException{
		int ID_Membre_Courant = Integer.parseInt(request.getParameter(ParametresServlet.ID_Membre_Courant));
//		String name = request.getParameter(ParametresServlet.Nom);
//		String prenom = request.getParameter(ParametresServlet.Prenom);
		String email = request.getParameter(ParametresServlet.Email);
		String vieuxMdp = request.getParameter("oldPassword");
		String mdp = request.getParameter(ParametresServlet.MotDePasse);
		String confirmMdp = request.getParameter(ParametresServlet.ConfirmerMotDePasse);
		System.out.println("Email : "+email+"\tVieuxMdp : "+vieuxMdp+"\tMdp : "+mdp+"\tConfirmMdp : "+confirmMdp);
//		String ville = request.getParameter(ParametresServlet.Ville);
		Base base = new Base();
		try {
			base.connect();
			Membre membre = new Membre(ID_Membre_Courant);
			membre.fill();
			String error = "";
			if(!email.contentEquals(membre.getsEmail())){
				base.procedureInit("Utilisateur_getUtilisateurByEmail", 1);
				base.setParamString("_sEmail", email);
				ResultSet testEmailAlreadyExists = base.executeQuery();
				boolean EmailAlreadyExists = testEmailAlreadyExists.first();
				if(EmailAlreadyExists){
					error = "Cette adresse mail est deja utilisee";
					
					request.setAttribute("errorMdpInvalide", "");
					request.setAttribute("errorMail", error);
					request.setAttribute("errorMdp", "");
					request.setAttribute("errorOldMdp", "");
				}
			}
			if(!vieuxMdp.contentEquals("")){
				if(!vieuxMdp.contentEquals(membre.getsPassword())){
					error = "Mot de passe incorrect";
					
					request.setAttribute("errorMdpInvalide", "");
					request.setAttribute("errorMail", "");
					request.setAttribute("errorMdp", "");
					request.setAttribute("errorOldMdp", error);
				}else{
					if(mdp.matches(".*[A-Z].*") && mdp.matches(".*[0-9].*") && mdp.matches(".*[a-z].*") && mdp.length()>=8){
						if(!mdp.contentEquals(confirmMdp)){
							error = "Les deux mots de passe tapes sont differents";
							
							request.setAttribute("errorMdpInvalide", "");
							request.setAttribute("errorMail", "");
							request.setAttribute("errorMdp", error);
							request.setAttribute("errorOldMdp", "");
						}
					}else{
						error = "Le mot de passe n'est pas valide";
												
						request.setAttribute("errorMdpInvalide", error);
						request.setAttribute("errorMail", "");
						request.setAttribute("errorMdp", "");
						request.setAttribute("errorOldMdp", "");
					}
				}
			}
			if(!error.contentEquals("")){
				
				request.setAttribute(ParametresServlet.Nom, membre.getsNom());
				request.setAttribute(ParametresServlet.Prenom, membre.getsPrenom());
				request.setAttribute(ParametresServlet.Email, membre.getsEmail());
				request.setAttribute(ParametresServlet.Ville, membre.getAdresse().getVille());
				
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
				
				getServletContext().getRequestDispatcher("/jsp/parametre.jsp").forward(request, response);
			}
//			membre.setsNom(name);
//			membre.setsPrenom(prenom);
//			membre.setAdresse(new Adresse(ville, null, null, null));
			membre.setsEmail(email);
			if(!vieuxMdp.contentEquals("")){
				membre.setPassword(mdp);
				membre.updatePassWord();
			}
			membre.insertOrUpdate();
			request.setAttribute(ParametresServlet.ID_Membre_Courant, ID_Membre_Courant);
			getServletContext().getRequestDispatcher("/seeCurrentUserProfile").forward(request, response);
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

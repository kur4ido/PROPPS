package com.polytech.propps.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.bdd.Base;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/Connexion")
public class Connexion extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet( HttpServletRequest request, HttpServletResponse
			response ) throws ServletException, IOException{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("EMAIL : "+email+"\tPASSWORD : "+password);
//		Base base = new Base();
//		try {
//			Base.initBase("jdbc:mysql://localhost:8889/PROPPS_DB", "propps#BDD!", "#aVjbBfTmJcT#");
//			base.connect();
//			base.procedureInit("Utilisateur_modifierAdresse", 5);
//			base.setParamInt("_ID_Utilisateur", 3);
//			base.setParamString("_sCodePostal", "94120");
//			base.setParamString("_sVille", "Coucou");
//			base.setParamString("_sPays", "Tu veux");
//			base.setParamString("_sAdresse", "voir ma bite?");
//			base.execute();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				base.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

}


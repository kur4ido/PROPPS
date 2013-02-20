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
import java.util.Arrays;
import java.util.List;

@WebServlet("/RechercheRapide")
public class RechercheRapide extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost( HttpServletRequest request, HttpServletResponse
			response ) throws ServletException, IOException{
		Base base = new Base();
		try {
			base.connect();
			String recherche = request.getParameter("quicksearch");
			ArrayList<Membre> resultList = Membre.rechercheRapide(recherche);
			ArrayList<String> stringList = new ArrayList<String>();
			List servResultList = Arrays.asList(resultList.toArray());
			for(Membre m : resultList){
				System.out.println(m.getsNom()+"\t"+m.getsPrenom()+"\t"+m.getsEmail());
				stringList.add(m.getsNom());
			}
			List servStringList = Arrays.asList(stringList.toArray());
			request.setAttribute("memberList", servResultList);
			request.setAttribute("stringList", servStringList);
			getServletContext().getRequestDispatcher("/jsp/result_recherche_membre.jsp").forward(request, response);			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			base.close();
		}
	}

}


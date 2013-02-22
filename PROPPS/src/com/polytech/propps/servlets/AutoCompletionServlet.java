package com.polytech.propps.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.models.Membre;
import com.polytech.propps.models.Societe;

public class AutoCompletionServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> servStringList = Arrays.asList(Societe.getArrayOfAll());

		request.setAttribute(ParametresServlet.StringList, servStringList);
		request.setAttribute("email", "");
		request.setAttribute("nom", "");
		request.setAttribute("prenom", "");
		request.setAttribute("ville", "");
		request.setAttribute("adresse", "");
		request.setAttribute("codePostal", "");
		request.setAttribute("pays", "");
		request.setAttribute(ParametresServlet.NomSociete, "");

		request.setAttribute("errorMdpInvalide", "");
		request.setAttribute("errorMail", "");
		request.setAttribute("errorMdp", "");
		getServletContext().getRequestDispatcher("/jsp/inscription_ent.jsp")
				.forward(request, response);

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}

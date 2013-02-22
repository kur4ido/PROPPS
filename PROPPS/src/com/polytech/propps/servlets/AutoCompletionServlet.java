package com.polytech.propps.servlets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.polytech.propps.models.Membre;
import com.polytech.propps.models.Societe;

public class AutoCompletionServlet extends HttpServlet {
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse
			response) {
		List servStringList = Arrays.asList(Societe.getArrayOfAll());
		request.setAttribute(ParametresServlet.StringList, servStringList);
	}
}

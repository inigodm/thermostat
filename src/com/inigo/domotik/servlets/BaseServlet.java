package com.inigo.domotik.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//TODO: should be syncrhonized?
	public void toDestiny(String destiny, boolean isForward, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if (isForward){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destiny);
			dispatcher.forward(request,response);
		}else{
			response.sendRedirect("/Thermostat"+ destiny);
		}
	}
}

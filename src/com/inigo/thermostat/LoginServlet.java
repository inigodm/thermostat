package com.inigo.thermostat;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Login login = new Login();
		String destiny = "/" + login.login(request.getParameter("username"), request.getParameter("password"));
		request.setAttribute("error", login.isError);
		request.getSession(true).setAttribute("user", login.user);
		toDestiny(destiny, login.isError, request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void toDestiny(String destiny, boolean isForward, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if (isForward){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destiny);
			dispatcher.forward(request,response);
		}else{
			response.sendRedirect("/Thermostat"+ destiny);
		}
	}
}

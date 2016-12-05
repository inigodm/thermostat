package com.inigo.domotik;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inigo.domotik.db.schema.SchemaCreator;
import com.inigo.domotik.exceptions.ThermostatException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Login login = new Login(request.getRemoteAddr());
		String destiny;
		SchemaCreator sc = new SchemaCreator();
		try {
			sc.createTables();
			destiny = "/" + login.login(request.getParameter("username"), request.getParameter("password"));
			request.setAttribute("error", login.isError);
		} catch (ThermostatException e) {
			e.printStackTrace();
			destiny = "/error";
		}
		request.getSession(true).setAttribute("user", login.user);
		toDestiny(destiny, login.isError, request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

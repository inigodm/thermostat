package com.inigo.domotik.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.inigo.domotik.db.dao.UserManager;
import com.inigo.domotik.exceptions.ThermostatException;
/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/site/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Changing password");
		String in = request.getParameter("pass");
        HttpSession session = ((HttpServletRequest) request).getSession(true);
		UserManager um = new UserManager();
		try {
			um.updatePassword((String) session.getAttribute("user"), in);
		} catch (ThermostatException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/site/changepassword.jsp");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
		
}

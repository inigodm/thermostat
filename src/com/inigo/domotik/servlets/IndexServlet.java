package com.inigo.domotik.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inigo.domotik.thermostat.threads.TemperatureMeasurer;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/site/index")
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TemperatureMeasurer tm = TemperatureMeasurer.getInstance();
		request.setAttribute("cpuTemp", (tm.getTemps().get(TemperatureMeasurer.TEMP_CPU_INDEX)));
		request.setAttribute("roomTemp", (tm.getTemps().get(TemperatureMeasurer.TEMP_ROOM_INDEX)));
		request.setAttribute("outTemp", (tm.getTemps().get(TemperatureMeasurer.TEMP_OUTSIDE_INDEX)));
		toDestiny("/site/index.jsp", true, request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

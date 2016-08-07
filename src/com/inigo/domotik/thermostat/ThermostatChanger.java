package com.inigo.domotik.thermostat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.inigo.domotik.thermostat.models.Petition;
import com.inigo.domotik.thermostat.models.ThermostatInfo;
import com.inigo.domotik.thread.thermostat.ThermostatManager;

/**
 * Servlet implementation class ThermostatChanger
 */
@WebServlet("/site/thermostat/changer")
public class ThermostatChanger extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ThermostatManager tm;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThermostatChanger() {
        super();
        tm = new ThermostatManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        Petition p = new Petition();
        p = gson.fromJson(br, Petition.class);
        System.out.println(" data " + p.getData());
        ThermostatInfo info = tm.increase(p.getData());
        response.getWriter().write(gson.toJson(info));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

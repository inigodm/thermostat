package com.inigo.domotik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.inigo.domotik.thermostat.models.Petition;
import com.inigo.domotik.thermostat.models.ThermostatInfo;


public abstract class RESTServlet<T>  extends HttpServlet{
	Class<T> in;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RESTServlet(Class<T> in){
		this.in = in;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRest(request, response);
	}
	
	protected void doRest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        T reqObject = gson.fromJson(br, in);
        response.getWriter().write(gson.toJson(doService(reqObject, request, response)));
	}
	
	protected abstract Object doService(T reqObject, HttpServletRequest request, HttpServletResponse response);
}

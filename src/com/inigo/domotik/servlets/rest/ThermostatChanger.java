package com.inigo.domotik.servlets.rest;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inigo.domotik.db.models.Petition;
import com.inigo.domotik.thread.thermostat.ThermostatManager;

/**
 * Servlet implementation class ThermostatChanger
 */
@WebServlet("/site/rest/thermostat/changer")
public class ThermostatChanger extends RESTServlet<Petition> {
	public ThermostatChanger() {
		super(Petition.class);
		tm = new ThermostatManager();
	}


	ThermostatManager tm;
	@Override
	protected Object get(List<String> pathP, Map<String, String> queryP, HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("dfdsfdsfsdsd");
		return null;
	}
	@Override
	protected Object post(Petition reqObject, HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(" data " + reqObject.getData());
        return tm.increase(((Double)reqObject.getData()).intValue());
	}

}

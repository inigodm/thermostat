package com.inigo.domotik.thermostat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inigo.domotik.RESTServlet;
import com.inigo.domotik.thermostat.models.Petition;
import com.inigo.domotik.thermostat.models.ThermostatInfo;

/**
 * Servlet implementation class ScheduleManager
 */
@WebServlet("/site/thermostat/scheduleManager")
public class ScheduleServlet extends RESTServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleServlet() {
        super(Object.class, Object.class);
    }
    
    @Override
	protected Object doService(Object reqObject, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
}

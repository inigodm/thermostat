package com.inigo.domotik.thermostat.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inigo.domotik.exceptions.ThermostatException;
import com.inigo.domotik.servlets.rest.RESTServlet;
import com.inigo.domotik.servlets.rest.models.Petition;
import com.inigo.domotik.thermostat.db.ThermostatDAO;
import com.inigo.domotik.thermostat.models.Schedule;

/**
 * Servlet implementation class ScheduleManager
 */
@WebServlet("/site/rest/tasks/*")
public class ScheduleServlet extends RESTServlet<Schedule>{
    
	public ScheduleServlet() {
		super(Schedule.class);
	}

	@Override
	protected Object get(List<String> pathP, Map<String, String> queryP,  HttpServletRequest request, HttpServletResponse response) throws IOException{
	
    	ThermostatDAO sm = new ThermostatDAO();
    	Object res = null;
    	try {
			res = sm.getSchedules();
		} catch (ThermostatException e) {
			e.printStackTrace();
		}
    	return res;
	}

	@Override
	protected Object post(Schedule reqObject, HttpServletRequest request, HttpServletResponse response) {
		ThermostatDAO sm = new ThermostatDAO();
		List<Schedule> res = null;
		try {
			sm.add(reqObject);
			sm.refreshSchedules();
			res = sm.getSchedules();
		} catch (ThermostatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	protected Object put(Schedule reqObject, HttpServletRequest request, HttpServletResponse response) {
		ThermostatDAO sm = new ThermostatDAO();
		List<Schedule> res = null;
		try {
			sm.update(reqObject);
			sm.refreshSchedules();
			res = sm.getSchedules();
		} catch (ThermostatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	protected Object delete(List<String> pathP, Map<String, String> queryP, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ThermostatDAO sm = new ThermostatDAO();
		List<Schedule> res = null;
		try {
			sm.delete(Integer.parseInt(pathP.get(0)));
			sm.refreshSchedules();
			res = sm.getSchedules();
		} catch (ThermostatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException | NullPointerException e){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		return res;
	}
}

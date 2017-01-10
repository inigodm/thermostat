package com.inigo.domotik.servlets.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inigo.domotik.db.managers.ScheduleManager;
import com.inigo.domotik.db.models.Schedule;
import com.inigo.domotik.exceptions.ThermostatException;
import com.inigo.domotik.servlets.rest.models.Petition;

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
	
    	ScheduleManager sm = new ScheduleManager();
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
		ScheduleManager sm = new ScheduleManager();
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
		ScheduleManager sm = new ScheduleManager();
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
		ScheduleManager sm = new ScheduleManager();
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

package com.inigo.domotik.servlets.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inigo.domotik.db.ScheduleManager;
import com.inigo.domotik.db.models.Petition;
import com.inigo.domotik.db.models.Schedule;
import com.inigo.domotik.exceptions.ThermostatException;

/**
 * Servlet implementation class ScheduleManager
 */
@WebServlet("/site/rest/tasks/")
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

class ScheduleManagerPetition extends Petition<Schedule>{
	
}

class ScheduleManagerResponse{
	int code;
	String msg;
	List<Object> response;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Object> getResponse() {
		return response;
	}
	public void setResponse(List<Object> response) {
		this.response = response;
	}
	
}


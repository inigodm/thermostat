package com.inigo.domotik.rest;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inigo.domotik.exceptions.ThermostatException;
import com.inigo.domotik.thermostat.db.ScheduleManager;
import com.inigo.domotik.thermostat.models.Petition;
import com.inigo.domotik.thermostat.models.db.Schedule;

/**
 * Servlet implementation class ScheduleManager
 */
@WebServlet("/site/rest/tasks/")
public class ScheduleServlet extends RESTServlet<Schedule>{
    
	public ScheduleServlet() {
		super(Schedule.class);
	}

	protected Object get() {
    	ScheduleManager sm = new ScheduleManager();
    	Object res = null;
    	System.out.println("asdadasda");
		/*try {
			switch (reqObject.getMethod()) {
			case "delete":
				sm.delete(reqObject.getData());
				break;
			case "update":
				sm.update(reqObject.getData());
				break;
			case "create":
				sm.add(reqObject.getData());
				break;
			case "getAll":
				res = sm.getSchedules();
				break;
			default:
				break;
			}
		} catch (ThermostatException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}*/
    	return res;
	}

	@Override
	protected Object get(Schedule reqObject, HttpServletRequest request, HttpServletResponse response) {
		return reqObject;
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


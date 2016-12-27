package com.inigo.domotik.rest;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.inigo.domotik.exceptions.ThermostatException;
import com.inigo.domotik.thermostat.db.ScheduleManager;
import com.inigo.domotik.thermostat.models.Petition;
import com.inigo.domotik.thermostat.models.db.Schedule;

/**
 * Servlet implementation class ScheduleManager
 */
@Path("/site/thermostat/scheduleManager")
public class ScheduleServlet{
    
	@GET
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


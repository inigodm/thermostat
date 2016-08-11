package com.inigo.domotik.thermostat;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inigo.domotik.RESTServlet;
import com.inigo.domotik.thermostat.models.Petition;
import com.inigo.domotik.thermostat.models.ThermostatInfo;
import com.inigo.domotik.thread.thermostat.ThermostatManager;

/**
 * Servlet implementation class ThermostatChanger
 */
@WebServlet("/site/thermostat/changer")
public class ThermostatChanger extends RESTServlet<ThermostatChagePetition> {
	private static final long serialVersionUID = 1L;
    ThermostatManager tm;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThermostatChanger() {
        super(ThermostatChagePetition.class);
        tm = new ThermostatManager();
    }
	
	@Override
	protected ThermostatInfo doService(ThermostatChagePetition reqObject, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(" data " + reqObject.getData());
        return tm.increase(reqObject.getData());
	}

}

class ThermostatChagePetition extends Petition<Integer>{
	
}

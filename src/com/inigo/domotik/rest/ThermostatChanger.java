package com.inigo.domotik.rest;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.inigo.domotik.thermostat.models.Petition;
import com.inigo.domotik.thermostat.models.ThermostatInfo;
import com.inigo.domotik.thread.thermostat.ThermostatManager;

/**
 * Servlet implementation class ThermostatChanger
 */
@Path("/thermostat/changer")
public class ThermostatChanger {
	ThermostatManager tm;
	
	public ThermostatChanger() {
        tm = new ThermostatManager();
    }	
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ThermostatInfo post(Petition<Integer> reqObject) {
		System.out.println(" data " + reqObject.getData());
        return tm.increase(reqObject.getData());
	}

}

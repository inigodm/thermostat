package com.inigo.domotik.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.inigo.domotik.thermostat.models.LogRequest;

@Path("/site/stats/get")
public class LogServlet {

	@GET
	@Path("/{fromDate}/{toDate}")
	protected Object get(@PathParam("fromDate")String from, @PathParam("toDate")String to) {
		LogRequest lr = new LogRequest()
				.setFromDate(from)
				.setToDate(to);
		return null;
	}

}

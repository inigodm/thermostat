package com.inigo.thermostat.thread;

import com.inigo.domotik.exceptions.ThermostatException;
import com.inigo.domotik.thermostat.db.LogManager;
import com.inigo.domotik.thermostat.models.db.Log;

public class TestLog {
	public void testAdd() throws ThermostatException{
		Log l = new Log();
		l.setDate("2016/12/23 12:12:12");
		l.setTemperature(12.444);
		l.setDesiredTemp(15);
		l.setActive(true);
		LogManager lm = new LogManager();
		lm.addLogger(l);
	}
}


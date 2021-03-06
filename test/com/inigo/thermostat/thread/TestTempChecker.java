package com.inigo.thermostat.thread;

import com.inigo.domotik.thermostat.threads.TemperatureMeasurer;
import com.inigo.testing.servlets.TestingServlet;

import junit.framework.TestCase;

public class TestTempChecker extends TestCase{
	public void testCPUTemp(){
		if (TestingServlet.request != null){
			TemperatureMeasurer tc = TemperatureMeasurer.getInstance();
			assertTrue(tc.rawTemps.get(TemperatureMeasurer.TEMP_CPU_INDEX).length()>2);
		}
	}
	
	public void testRoomTemp(){
		if (TestingServlet.request != null){
			TemperatureMeasurer tc = TemperatureMeasurer.getInstance();
			assertTrue(tc.rawTemps.get(TemperatureMeasurer.TEMP_ROOM_INDEX).length() > 0);
		}
	}
}

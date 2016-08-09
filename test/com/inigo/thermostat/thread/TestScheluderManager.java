package com.inigo.thermostat.thread;

import java.util.Date;

import com.inigo.domotik.exceptions.ThermostatException;
import com.inigo.domotik.thermostat.db.ScheduleManager;
import com.inigo.domotik.thermostat.models.db.Schedule;

import junit.framework.TestCase;

public class TestScheluderManager extends TestCase{
	
	
	public void testNewShecule() throws ThermostatException{
		Schedule s = new Schedule();
		s.setDesiredTemp(25);
		s.setFromDate((new Date()).getTime());
		s.setToDate(15);
		s.setMaxHour(23);
		s.setMinHour(20);
		ScheduleManager sm = new ScheduleManager();
		s = sm.add(s);
		int id = s.getId();
	}
	
	public void testDeleteShecule(){
		
	}
	
	public void testGetShecule(){
		
	}
	
	public void testGetSheculeList(){
		
	}
}

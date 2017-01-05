package com.inigo.thermostat.thread;

import java.util.List;

import com.inigo.domotik.db.ScheduleManager;
import com.inigo.domotik.db.models.Schedule;
import com.inigo.domotik.exceptions.ThermostatException;

import junit.framework.TestCase;

public class TestScheluderManager extends TestCase{
	
	
	public void testNewShecule() throws ThermostatException{
		Schedule s = new Schedule();
		s.setDesiredTemp(25);
		s.setMaxHour("23");
		s.setMinHour("20");
		s.setWeekdays("LMXJV");
		ScheduleManager sm = new ScheduleManager();
		s = sm.add(s);
		int id = s.getId();
	}
	
	public void testDeleteShecule() throws ThermostatException{
		Schedule s = new Schedule();
		s.setId(0);
		ScheduleManager sm = new ScheduleManager();
		//assertFalse(-1 == sm.delete(s));
	}
	
	public void testGetShecule(){
		ScheduleManager sm = new ScheduleManager();
		
	}
	
	public void testGetSheculeList() throws ThermostatException{
		ScheduleManager sm = new ScheduleManager();
		List<Schedule> schedules = sm.getSchedules();
		assertTrue(schedules.size() > 0);
		schedules = sm.getSchedules(2, 0);
		assertTrue(schedules.size() == 2);
	}
}

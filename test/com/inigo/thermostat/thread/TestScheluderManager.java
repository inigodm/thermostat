package com.inigo.thermostat.thread;

import java.util.List;

import com.inigo.domotik.exceptions.ThermostatException;
import com.inigo.domotik.thermostat.db.ThermostatDAO;
import com.inigo.domotik.thermostat.models.Schedule;

import junit.framework.TestCase;

public class TestScheluderManager extends TestCase{
	
	public void testCreateTable() throws ThermostatException{
		ThermostatDAO sm = new ThermostatDAO();
		sm.createTable();
	}
	
	public void testNewShecule() throws ThermostatException{
		Schedule s = new Schedule();
		s.setDesiredTemp(25);
		s.setMaxHour("23");
		s.setMinHour("20");
		s.setWeekdays("LMXJV");
		ThermostatDAO sm = new ThermostatDAO();
		s = sm.add(s);
		int id = s.getId();
	}
	
	public void testDeleteShecule() throws ThermostatException{
		Schedule s = new Schedule();
		s.setId(0);
		ThermostatDAO sm = new ThermostatDAO();
		//assertFalse(-1 == sm.delete(s));
	}
	
	public void testGetSheculeList() throws ThermostatException{
		ThermostatDAO sm = new ThermostatDAO();
		List<Schedule> schedules = sm.getSchedules();
		assertTrue(schedules.size() > 0);
	}
}

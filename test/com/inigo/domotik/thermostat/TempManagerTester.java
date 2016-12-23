package com.inigo.domotik.thermostat;

import com.inigo.domotik.thread.thermostat.ThermostatManager;

import junit.framework.TestCase;

public class TempManagerTester extends TestCase{
	public void testDesTempChange(){
		ThermostatManager tm = new ThermostatManager();
		int desTemp = tm.getDesiredTemp();
		tm.increase(1);
		assertEquals(desTemp +  1, tm.getDesiredTemp());
		tm.increase(-1);
		assertEquals(desTemp, tm.getDesiredTemp());
	}
	
	public void testActivateOnTempChange(){
		ThermostatManager tm = new ThermostatManager();
		int desTemp = tm.getDesiredTemp();
		int actTemp = tm.getActualTemp();
		assertEquals(tm.isActive(), actTemp < desTemp);
	}
	
	public void testSetDesTemp(){
		ThermostatManager tm = new ThermostatManager();
		tm.setDesiredTemp(15);
		assertEquals(tm.isActive(), tm.getActualTemp() < tm.getDesiredTemp());
		//tm.setDesiredTemp(1);
		//assertEquals(tm.isActive(), tm.getDesiredTemp() < tm.getActualTemp());
	}
}

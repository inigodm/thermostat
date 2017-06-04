package com.inigo.domotik.thermostat;

import com.inigo.domotik.thermostat.threads.TemperatureMeasurer;

import junit.framework.TestCase;

public class TempManagerTester extends TestCase{
	public void testDesTempChange(){
		TemperatureMeasurer tm = TemperatureMeasurer.getInstance();
		int desTemp = tm.getDesiredTemp();
		tm.increase(1);
		assertEquals(desTemp +  1, tm.getDesiredTemp());
		tm.increase(-1);
		assertEquals(desTemp, tm.getDesiredTemp());
	}
	
	public void testActivateOnTempChange(){
		TemperatureMeasurer tm = TemperatureMeasurer.getInstance();
		int desTemp = tm.getDesiredTemp();
		int actTemp = tm.getTemp(TemperatureMeasurer.TEMP_ROOM_INDEX);
		assertEquals(tm.isActive(), actTemp < desTemp);
	}
	
	public void testSetDesTemp(){
		TemperatureMeasurer tm = TemperatureMeasurer.getInstance();
		tm.setDesiredTemp(15);
		assertEquals(tm.isActive(), tm.getTemp(TemperatureMeasurer.TEMP_ROOM_INDEX) < tm.getDesiredTemp());
		//tm.setDesiredTemp(1);
		//assertEquals(tm.isActive(), tm.getDesiredTemp() < tm.getActualTemp());
	}
}

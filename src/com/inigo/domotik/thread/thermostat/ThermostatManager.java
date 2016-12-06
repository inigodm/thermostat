package com.inigo.domotik.thread.thermostat;

import com.inigo.domotik.thermostat.models.ThermostatInfo;

public class ThermostatManager {
	TemperatureMeasurer tc = new TemperatureMeasurer();
	int desiredTemp = 0;
	
	public int getDesiredTemp() {
		return this.desiredTemp;
	}
	public void setDesiredTemp(int desiredTemp) {
		this.desiredTemp = desiredTemp;
	}
	public int getActualTemp() {
		return tc.getTemp(TemperatureMeasurer.TEMP_ROOM_INDEX);
	}
	public ThermostatInfo increase(int i) {
		ThermostatInfo info = new ThermostatInfo();
		this.desiredTemp = desiredTemp + i;
		info.setDesiredTemp("" + desiredTemp);
		info.setRoomTemp(""+ tc.getTemp(TemperatureMeasurer.TEMP_CPU_INDEX));
		System.out.println(tc.getDesiredTemp() + " deseados, tenemos " + tc.getTemp(TemperatureMeasurer.TEMP_ROOM_INDEX));
		return info;
	}
	public boolean isActive() {
		return tc.isActive();
	}
}

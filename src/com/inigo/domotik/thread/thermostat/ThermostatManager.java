package com.inigo.domotik.thread.thermostat;

import com.inigo.domotik.thermostat.models.ThermostatInfo;

public class ThermostatManager {
	TemperatureMeasurer tc = new TemperatureMeasurer();
	
	public int getDesiredTemp() {
		return TemperatureMeasurer.getDesiredTemp();
	}
	public void setDesiredTemp(int desiredTemp) {
		TemperatureMeasurer.setDesiredTemp(desiredTemp);
	}
	public int getActualTemp() {
		return tc.getTemp(TemperatureMeasurer.TEMP_ROOM_INDEX);
	}
	public ThermostatInfo increase(int i) {
		ThermostatInfo info = new ThermostatInfo();
		TemperatureMeasurer.setDesiredTemp(TemperatureMeasurer.getDesiredTemp() + i);
		info.setDesiredTemp("" + tc.getDesiredTemp());
		info.setRoomTemp(""+ tc.getTemp(TemperatureMeasurer.TEMP_CPU_INDEX));
		System.out.println(tc.getDesiredTemp() + " deseados, tenemos " + tc.getTemp(TemperatureMeasurer.TEMP_ROOM_INDEX));
		return info;
	}
	public boolean isActive() {
		return tc.isActive();
	}
}

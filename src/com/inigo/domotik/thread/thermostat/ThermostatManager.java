package com.inigo.domotik.thread.thermostat;

import com.inigo.domotik.thermostat.models.ThermostatInfo;

public class ThermostatManager {
	TemperatureMeasurer tc = TemperatureMeasurer.getInstance();
	
	public int getDesiredTemp() {
		return tc.getDesiredTemp();
	}
	public void setDesiredTemp(int desiredTemp) {
		tc.setDesiredTemp(desiredTemp);
	}
	public int getActualTemp() {
		return tc.getTemp(TemperatureMeasurer.TEMP_ROOM_INDEX);
	}
	public ThermostatInfo increase(int i) {
		ThermostatInfo info = new ThermostatInfo();
		tc.setDesiredTemp(tc.getDesiredTemp() + i);
		info.setDesiredTemp("" + tc.getDesiredTemp());
		info.setRoomTemp(""+ tc.getRawTemp(TemperatureMeasurer.TEMP_ROOM_INDEX));
		info.setOn(isActive());
		return info;
	}
	public boolean isActive() {
		return tc.isActive();
	}
}

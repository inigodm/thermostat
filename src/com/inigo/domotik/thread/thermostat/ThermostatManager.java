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
		return TemperatureMeasurer.getTemp(TemperatureMeasurer.TEMP_ROOM_INDEX);
	}
	public ThermostatInfo increase(int i) {
		ThermostatInfo info = new ThermostatInfo();
		TemperatureMeasurer.setDesiredTemp(TemperatureMeasurer.getDesiredTemp() + i);
		info.setDesiredTemp("" + TemperatureMeasurer.getDesiredTemp());
		info.setRoomTemp(""+ TemperatureMeasurer.getTemp(TemperatureMeasurer.TEMP_ROOM_INDEX));
		return info;
	}
	public boolean isActive() {
		return TemperatureMeasurer.isActive();
	}
}

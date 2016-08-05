package com.inigo.domotik.thread.thermostat;

public class ThermostatManager {
	TemperatureMeasurer tc = new TemperatureMeasurer();
	
	public int getDesiredTemp() {
		return TemperatureMeasurer.getDesiredTemp();
	}
	public void setDesiredTemp(int desiredTemp) {
		TemperatureMeasurer.setDesiredTemp(desiredTemp);
	}
	public int getActualTemp() {
		return tc.getTemp(TemperatureMeasurer.TEMP_CPU_INDEX);
	}
	public void increase(int i) {
		TemperatureMeasurer.setDesiredTemp(TemperatureMeasurer.getDesiredTemp() + i);
	}
	public boolean isActive() {
		return tc.isActive();
	}
}

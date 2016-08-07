package com.inigo.domotik.thermostat.models;

public class ThermostatInfo {
	String roomTemp;
	String desiredTemp;
	public String getRoomTemp() {
		return roomTemp;
	}
	public void setRoomTemp(String roomTemp) {
		this.roomTemp = roomTemp;
	}
	public String getDesiredTemp() {
		return desiredTemp;
	}
	public void setDesiredTemp(String desiredTemp) {
		this.desiredTemp = desiredTemp;
	}
	
}

package com.inigo.domotik.thermostat.servlets.models;

public class ThermostatInfo {
	String roomTemp;
	String desiredTemp;
	String outsideTemp;
	boolean isOn;
	
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
	public boolean isOn() {
		return isOn;
	}
	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}
	public String getOutsideTemp() {
		return outsideTemp;
	}
	public void setOutsideTemp(String outsideTemp) {
		this.outsideTemp = outsideTemp;
	}	
}


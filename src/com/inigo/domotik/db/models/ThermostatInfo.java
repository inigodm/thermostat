package com.inigo.domotik.db.models;

public class ThermostatInfo {
	String roomTemp;
	String desiredTemp;
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
	
}


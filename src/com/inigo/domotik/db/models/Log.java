package com.inigo.domotik.db.models;

public class Log {
	String date;
	double temperature;
	int desiredTemp;
	int active;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public int getDesiredTemp() {
		return desiredTemp;
	}
	public void setDesiredTemp(int desiredTemp) {
		this.desiredTemp = desiredTemp;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public void setActive(boolean active) {
		this.active = active?1:0;
	}
	@Override
	public String toString() {
		return String.format("{'date':'%s', 'temperature':'%s', 'desiredTemp':'%s', 'active':'%s'}\n", date, temperature, desiredTemp,
				active);
	}	
}

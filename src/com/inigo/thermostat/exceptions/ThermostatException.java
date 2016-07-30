package com.inigo.thermostat.exceptions;

public class ThermostatException extends Exception{

	public ThermostatException(String message, Exception e) {
		super(message, e);
	}

}

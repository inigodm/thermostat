package com.inigo.domotik.exceptions;

public class ThermostatException extends Exception{

	public ThermostatException(String message, Exception e) {
		super(message, e);
	}

	public ThermostatException(String message) {
		super(message);
	}

}

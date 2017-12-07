package com.inigo.domotik.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.inigo.domotik.db.CustomConnection;
import com.inigo.domotik.exceptions.ThermostatException;

public class ThermostatProperties {
	static Properties p = null;
	
	public ThermostatProperties() throws ThermostatException{
		if (p == null){
			loadProperties();
		}
	}
	
	private void loadProperties() throws ThermostatException{
		try {
			Properties p = new Properties();
			p.load(obtainPropertiesInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ThermostatException("A problem has occured when opening thermostat.properties");	
		}
	}
	
	private InputStream obtainPropertiesInputStream() throws ThermostatException {
		InputStream is = CustomConnection.class.getClassLoader().getResourceAsStream("thermostat.properties");
		if (is == null){
			throw new ThermostatException("thermostat.properties file does not exist");	
		}
		return is;
	}
}

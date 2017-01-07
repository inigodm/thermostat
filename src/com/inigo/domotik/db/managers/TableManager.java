package com.inigo.domotik.db.managers;

import com.inigo.domotik.exceptions.ThermostatException;

public interface TableManager {
	public void createTable() throws ThermostatException;
}

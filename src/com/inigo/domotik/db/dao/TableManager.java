package com.inigo.domotik.db.dao;

import com.inigo.domotik.exceptions.ThermostatException;

/** Whoever use a table is responsable of create it
 * @author inigo
 *
 */
public interface TableManager {
	public void createTable() throws ThermostatException;
}

package com.inigo.domotik.thermostat.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.inigo.domotik.db.CustomConnection;
import com.inigo.domotik.exceptions.ThermostatException;
import com.inigo.domotik.thermostat.models.db.Schedule;

public class ScheduleManager {

	public Schedule add(Schedule s) throws ThermostatException {
		String sql= "insert into schedules (USERID, FROMDATE, " + 
                " TODATE, STARTHOUR, ENDHOUR, DESIREDTEMP) values"
                + " (?,?,?,?,?,?)";
		try (Connection conn = CustomConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, s.getId());
			stmt.setLong(2, s.getFromDate());
			stmt.setLong(3, s.getToDate());
			stmt.setInt(4, s.getMinHour());
			stmt.setInt(5, s.getMaxHour());
			stmt.setInt(6, s.getDesiredTemp());
			System.out.println("Me devuelve esto" + stmt.execute());
		} catch (SQLException e) {
			throw new ThermostatException(e.getMessage(), e);
		}
		return s;
	}

}

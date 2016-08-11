package com.inigo.domotik.thermostat.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			System.out.println("Me devuelve esto" + stmt.executeUpdate());
		} catch (Exception e) {
			throw new ThermostatException(e.getMessage(), e);
		}
		return s;
	}
	
	public Schedule update(Schedule s) throws ThermostatException {
		String sql= "update schedules set USERID=?, FROMDATE=?, " + 
                " TODATE=?, STARTHOUR=?, ENDHOUR=?, DESIREDTEMP=? where "
                + " rowid = ?";
		try (Connection conn = CustomConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, s.getUserId());
			stmt.setLong(2, s.getFromDate());
			stmt.setLong(3, s.getToDate());
			stmt.setInt(4, s.getMinHour());
			stmt.setInt(5, s.getMaxHour());
			stmt.setInt(6, s.getDesiredTemp());
			stmt.setInt(7, s.getId());
			System.out.println("Me devuelve esto" + stmt.executeUpdate());
		} catch (Exception e) {
			throw new ThermostatException(e.getMessage(), e);
		}
		return s;
	}

	public int delete(Schedule s) throws ThermostatException {
		int res = -1;
		String sql= "delete from schedules where oid=?";
		try (Connection conn = CustomConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql)){
		stmt.setInt(1, s.getId());
		res = stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			throw new ThermostatException(e.getMessage(), e);
		}
		return res;
	}

	public List<Schedule> getSchedules() throws ThermostatException {
		return getSchedules(-1, -1);
	}

	public List<Schedule> getSchedules(int limit, int offset) throws ThermostatException {
		String sql = "";
		if (limit != -1 && offset != -1){
			sql = " LIMIT " + limit + " OFFSET " + offset;
		}
		sql = "select rowid, userid, fromdate, todate, starthour, endhour,"
				+ "desiredtemp from schedules" + sql;
		List<Schedule> schedules = new ArrayList<>();
		try (Connection conn = CustomConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			while (rs.next()){
				schedules.add(getSchedule(rs));
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new ThermostatException(e.getMessage(), e);
		}
		return schedules;
	}
	
	private Schedule getSchedule(ResultSet rs) throws SQLException{
		Schedule res = new Schedule();
		res.setId(rs.getInt("rowid"));
		res.setFromDate(rs.getLong("fromdate"));
		res.setDesiredTemp(rs.getInt("desiredtemp"));
		res.setMaxHour(rs.getInt("endhour"));
		res.setMinHour(rs.getInt("starthour"));
		res.setToDate(rs.getLong("todate"));
		res.setUserId(rs.getInt("userid"));
		return res;
	}

}

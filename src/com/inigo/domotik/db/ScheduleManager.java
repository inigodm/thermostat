package com.inigo.domotik.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.inigo.domotik.db.models.Schedule;
import com.inigo.domotik.exceptions.ThermostatException;

public class ScheduleManager {

	public synchronized Schedule add(Schedule s) throws ThermostatException {
		String sql= "insert into schedules (FROMDATE, " + 
                " TODATE, WEEKDAYS, STARTHOUR, ENDHOUR, DESIREDTEMP, ACTIVE) values"
                + " (?,?,?,?,?,?,?)";
		try (Connection conn = CustomConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, s.getFromDate()+"");
			stmt.setString(2, s.getToDate()+"");
			stmt.setString(3, s.getWeekdays());
			stmt.setString(4, s.getMinHour());
			stmt.setString(5, s.getMaxHour());
			stmt.setInt(6, s.getDesiredTemp());
			stmt.setInt(7, s.getActive());
			System.out.println("Me devuelve esto" + stmt.executeUpdate());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ThermostatException(e.getMessage(), e);
		}
		return s;
	}
	
	public synchronized Schedule update(Schedule s) throws ThermostatException {
		String sql= "update schedules set ACTIVE=?, FROMDATE=?, " + 
                " TODATE=?, STARTHOUR=?, ENDHOUR=?, WEEKDAYS=?, DESIREDTEMP=? where "
                + " rowid = ?";
		try (Connection conn = CustomConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, s.getActive());
			stmt.setString(2, s.getFromDate());
			stmt.setString(3, s.getToDate());
			stmt.setString(4, s.getMinHour());
			stmt.setString(5, s.getMaxHour());
			stmt.setString(6, s.getWeekdays());
			stmt.setInt(7, s.getDesiredTemp());
			stmt.setInt(8, s.getId());
			System.out.println("Me devuelve esto" + stmt.executeUpdate());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ThermostatException(e.getMessage(), e);
		}
		return s;
	}

	public synchronized int delete(int s) throws ThermostatException {
		int res = -1;
		String sql= "delete from schedules where oid=?";
		try (Connection conn = CustomConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql)){
		stmt.setInt(1, s);
		res = stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			throw new ThermostatException(e.getMessage(), e);
		}
		return res;
	}

	public synchronized List<Schedule> getSchedules() throws ThermostatException {
		return getSchedules(-1, -1);
		/*Schedule s = new Schedule();
		s.setDesiredTemp(22);
		s.setMinHour("2");
		s.setMaxHour("3");
		s.setFromDate("12");
		s.setToDate("25");
		List<Schedule> l = new ArrayList<>();
		l.add(s);
		l.add(s);
		return l;*/
	}

	public List<Schedule> getSchedules(int limit, int offset) throws ThermostatException {
		String sql = "";
		if (limit != -1 && offset != -1){
			sql = " LIMIT " + limit + " OFFSET " + offset;
		}
		sql = "select rowid, active, fromdate, todate, weekdays, starthour, endhour,"
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
		res.setFromDate(rs.getString("fromdate"));
		res.setDesiredTemp(rs.getInt("desiredtemp"));
		res.setMaxHour(rs.getString("endhour"));
		res.setMinHour(rs.getString("starthour"));
		res.setToDate(rs.getString("todate"));
		res.setWeekdays(rs.getString("weekdays"));
		res.setActive(rs.getInt("active"));
		return res;
	}

}

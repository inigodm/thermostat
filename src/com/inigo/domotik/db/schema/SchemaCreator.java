package com.inigo.domotik.db.schema;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.inigo.domotik.db.CustomConnection;
import com.inigo.domotik.exceptions.ThermostatException;
import com.inigo.domotik.utils.DBUtils;

public class SchemaCreator {
	public void createSchema() throws ThermostatException{
		String sql = "select user, pass, userid from users";
		try {
			DBUtils.executeUpdate("drop table schedules");
			DBUtils.executeUpdate("drop table users");
			executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("trying to generate tables");
			createUserTable();
			createSchedulesTable();
			addData();
		}
	}

	private void addData() throws ThermostatException {
		String sql = "insert into users (user, pass) values ('inigo', 'password')";
		DBUtils.executeUpdate(sql);
	}

	private void createSchedulesTable() throws ThermostatException {
		String sql = "CREATE TABLE schedules " +
                "(SCHEDULEID INTEGER PRIMARY KEY     NOT NULL," +
                " USERID           INTEGER    NOT NULL, " + 
                " FROMDATE           INTEGER    NOT NULL, " + 
                " TODATE           INTEGER    NOT NULL, " + 
                " STARTHOUR           INTEGER    NOT NULL, " + 
                " ENDHOUR           INTEGER    NOT NULL, " + 
                " MINTEMP           INTEGER    NOT NULL, " + 
                " MAXTEMP          INTEGER    NOT NULL)";
		DBUtils.executeUpdate(sql);
	}

	private void createUserTable() throws ThermostatException {
		String sql = "CREATE TABLE USERS " +
                "(USERID INTEGER PRIMARY KEY     NOT NULL," +
                " USER           TEXT    NOT NULL, " + 
                " PASS           TEXT     NOT NULL)";
		DBUtils.executeUpdate(sql);
	}
	public static ResultSet executeQuery(String sql) throws ThermostatException{
		ResultSet rs = null;
		try (Connection conn = CustomConnection.getConnection();
			Statement stmt = conn.createStatement()){
			rs = stmt.executeQuery(sql);
		}catch (SQLException e){
			throw new ThermostatException(e.getMessage(), e);
		}
		return rs;
	}
}

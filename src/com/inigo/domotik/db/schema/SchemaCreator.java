package com.inigo.domotik.db.schema;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.inigo.domotik.db.CustomConnection;
import com.inigo.domotik.exceptions.ThermostatException;
import com.inigo.domotik.utils.DBUtils;

public class SchemaCreator {
	public void createSchema() throws ThermostatException{
		String sql = "select user, pass, userid from users";
		try {
			DBUtils.executeUpdate("drop table schedules");
			DBUtils.executeUpdate("drop table users");
			DBUtils.executeUpdate("drop table logs");
			executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("trying to generate tables");
			createUserTable();
			createSchedulesTable();
			createLogTable();
			addData();
		}
	}
	
	public void createTables() throws ThermostatException{
		String sql = "select user, pass, userid from users";
		try{
			DBUtils.executeUpdate("drop table schedules");
			DBUtils.executeUpdate("drop table users");
			DBUtils.executeUpdate("drop table logs");
		}catch (Exception e){
			
		}
		System.out.println("trying to generate tables");
		createUserTable();
		createSchedulesTable();
		createLogTable();
		addData();
	}

	private void addData() throws ThermostatException {
		Random rnd = new Random();
		String sql = "insert into users (user, pass, salt) values ('inigo', 'password', '" + rnd.nextLong() +"')";
		DBUtils.executeUpdate(sql);
	}

	private void createSchedulesTable() throws ThermostatException {
		String sql = "CREATE TABLE schedules " +
                "(FROMDATE           TEXT    NOT NULL, " + 
                " TODATE           TEXT    NOT NULL, " + 
                " STARTHOUR           TEXT    NOT NULL, " + 
                " ENDHOUR           TEXT    NOT NULL, " + 
                " DESIREDTEMP           INTEGER    NOT NULL, " +
                " ACTIVE           INTEGER    NOT NULL)";
		DBUtils.executeUpdate(sql);
	}
	
	private void createLogTable() throws ThermostatException {
		String sql = "CREATE TABLE logs " +
                "(DATE           TEXT    NOT NULL, " + 
                " TEMPERATURE           REAL    NOT NULL)";
		DBUtils.executeUpdate(sql);
	}

	private void createUserTable() throws ThermostatException {
		String sql = "CREATE TABLE USERS " +
                "(USER           TEXT    NOT NULL, " + 
                " PASS           TEXT     NOT NULL," + 
                " SALT			TEXT  	   NOT NULL)";
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

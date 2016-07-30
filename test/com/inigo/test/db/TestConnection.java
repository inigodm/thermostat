package com.inigo.test.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.inigo.thermostat.db.CustomConnection;
import com.inigo.thermostat.db.schema.SchemaCreator;
import com.inigo.thermostat.exceptions.ThermostatException;

import junit.framework.TestCase;

public class TestConnection extends TestCase{
	public void testConnection() throws ThermostatException, SQLException{
		Connection conn = CustomConnection.getConnection();
		assertEquals(false, conn.isClosed());
		conn.close();
	}
	
	public void testCreateTable() throws ThermostatException, SQLException{
		String sql = "CREATE TABLE COMPANY " +
                "(ID INTEGER PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL, " + 
                " AGE            INT     NOT NULL, " + 
                " ADDRESS        CHAR(50), " + 
                " SALARY         REAL)"; 
		Connection conn = CustomConnection.getConnection();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
	    stmt.close();
	    conn.close();
	}
	
	public void testDropTable() throws ThermostatException, SQLException{
		String sql = "DROP TABLE COMPANY "; 
		try (Connection conn = CustomConnection.getConnection()){
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		    stmt.close();
		}
	}
	
	public void testCreateSchema() throws SQLException, ThermostatException{
		SchemaCreator sc = new SchemaCreator();
		sc.createSchema();
		String sql = "select user, pass, userid from users";
		getData(sql);
		sql = "select fromDate, toDate, startHour, endHour, minTemp, maxTemp, userid, scheduleid from schedules";
		getData(sql);
		
		
	}
	
	public ResultSet getData(String sql) throws SQLException, ThermostatException{
		ResultSet rs = null;
		try (Connection conn = CustomConnection.getConnection();
			Statement stmt = conn.createStatement()){
			rs = stmt.executeQuery(sql);
		}
		return rs;
	}

}

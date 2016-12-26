package com.inigo.test.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.inigo.domotik.db.CustomConnection;
import com.inigo.domotik.db.schema.SchemaCreator;
import com.inigo.domotik.exceptions.ThermostatException;

import junit.framework.TestCase;

public class TestConnection extends TestCase{
	public synchronized void testConnection() throws ThermostatException, SQLException{
		Connection conn = CustomConnection.getConnection();
		assertEquals(false, conn.isClosed());
		conn.close();
	}
	
	public synchronized void testCreateTable() throws ThermostatException, SQLException{
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
	
	public synchronized void testDropTable() throws ThermostatException, SQLException{
		String sql = "DROP TABLE COMPANY "; 
		try (Connection conn = CustomConnection.getConnection()){
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		    stmt.close();
		}
	}
	
	public synchronized void testCreateSchema() throws SQLException, ThermostatException{
		try{
			String sql = "select user, pass, userid from users";
			getData(sql);
			sql = "select fromDate, toDate, startHour, endHour, minTemp, maxTemp, userid, scheduleid from schedules";
			getData(sql);
		}catch(Exception e){
			SchemaCreator sc = new SchemaCreator();
			sc.createSchema();	
		}
	}
	
	public ResultSet getData(String sql) throws SQLException, ThermostatException{
		ResultSet rs = null;
		try (Connection conn = CustomConnection.getConnection();
			Statement stmt = conn.createStatement()){
			rs = stmt.executeQuery(sql);
		}catch(Exception e){
			throw e;
		}
		return rs;
	}

}

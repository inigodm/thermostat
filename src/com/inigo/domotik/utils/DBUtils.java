package com.inigo.domotik.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.inigo.domotik.db.CustomConnection;
import com.inigo.domotik.exceptions.ThermostatException;

public class DBUtils {
	public synchronized static void executeUpdate(String sql) throws ThermostatException{
		try(Connection conn = CustomConnection.getConnection();
		Statement stmt = conn.createStatement()){
		stmt.executeUpdate(sql);
		}catch (SQLException e){
			e.printStackTrace();
			throw new ThermostatException(e.getMessage(), e);
		}
	}	
	
	public synchronized static ResultSet executeQuery(String sql) throws ThermostatException{
		ResultSet rs = null;
		try (Connection conn = CustomConnection.getConnection();
			Statement stmt = conn.createStatement()){
			rs = stmt.executeQuery(sql);
		}catch (SQLException e){
			e.printStackTrace();
			throw new ThermostatException(e.getMessage(), e);
		}
		return rs;
	}
	
}

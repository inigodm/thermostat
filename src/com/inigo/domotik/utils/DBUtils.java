package com.inigo.domotik.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.inigo.domotik.db.CustomConnection;
import com.inigo.domotik.exceptions.ThermostatException;

public class DBUtils {
	public static void executeUpdate(String sql) throws ThermostatException{
		try(Connection conn = CustomConnection.getConnection();
		Statement stmt = conn.createStatement()){
		stmt.executeUpdate(sql);
		}catch (SQLException e){
			throw new ThermostatException(e.getMessage(), e);
		}
	}
}

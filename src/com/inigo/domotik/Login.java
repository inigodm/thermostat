package com.inigo.domotik;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.inigo.domotik.db.CustomConnection;
import com.inigo.domotik.exceptions.ThermostatException;

public class Login {
	public boolean isError;
	public String user;

	public Login(String remoteIp){
		if ("127.0.0.1".equals(remoteIp)){
			this.user = "inigo";
		}
	}
	
	public String login(String username, String pass) throws ThermostatException {
		if (this.user != null){
			return "site/index";
		}
		//isError = !("inigo".equals(username) && "password".equals(pass));
		String user = findUser(username, pass);
		isError = (null == user);
		if (username == null && pass == null){
			isError = false;
			return "login.jsp"; 
		}
		if (isError){
			return  "login.jsp";
		}else{
			this.user = username;
			return "site/index";
		}
	}
	
	private synchronized String findUser(String user, String pass) throws ThermostatException{
		try(Connection conn = CustomConnection.getConnection();
			PreparedStatement stmt = createPreparedStatement(conn, user.toLowerCase(), pass);
			ResultSet rs = stmt.executeQuery()){
			String res = null;
			if (rs.next()){
				res = rs.getString(1);
			}
			return res;
		}catch(SQLException e){
			throw new ThermostatException(e.getMessage(), e);
		}
	}
	
	private PreparedStatement createPreparedStatement(Connection conn, String user, String pass) throws SQLException{
		String sql = "select user from users where user = ? and pass = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, user);
		stmt.setString(2, pass);
		return stmt;
	}

}

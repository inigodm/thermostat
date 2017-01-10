package com.inigo.domotik.db.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.inigo.domotik.db.CustomConnection;
import com.inigo.domotik.exceptions.ThermostatException;
import com.inigo.domotik.utils.DBUtils;

public class UserManager implements TableManager{
	public boolean isError;
	public String user;
	
	public UserManager(String remoteIp){
		if ("127.0.0.1".equals(remoteIp)){
			this.user = "inigo";
		}
	}
	
	public UserManager(){}
	
	@Override
	public void createTable() throws ThermostatException{
		try{
			System.out.println("Deleting table users");
			DBUtils.executeUpdate("drop table users");
		}catch (Exception e){
			
		}
		System.out.println("trying to generate table Users");
		createUserTable();
		System.out.println("Adding a user");
		addData();
		System.out.println("Done");
	}
	private void createUserTable() throws ThermostatException {
		String sql = "CREATE TABLE USERS " +
                "(USER           TEXT    NOT NULL, " + 
                " PASS           TEXT     NOT NULL," + 
                " SALT			TEXT  	   NOT NULL)";
		DBUtils.executeUpdate(sql);
	}
	
	//TODO: add salted hash validation
	private void addData() throws ThermostatException {
		Random rnd = new Random();
		String sql = "insert into users (user, pass, salt) values ('inigo', 'password', '" + rnd.nextLong() +"')";
		DBUtils.executeUpdate(sql);
	}
	
	public String login(String username, String pass) throws ThermostatException {
		if (this.user != null){
			return "site/index";
		}
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
	
	//TODO: add salted hash validation
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

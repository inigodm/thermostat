package com.inigo.domotik.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.inigo.domotik.db.CustomConnection;
import com.inigo.domotik.exceptions.ThermostatException;
import com.inigo.domotik.utils.DBUtils;
import com.inigo.domotik.utils.SHA256;

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
	
	public void updatePassword(String user, String pass) throws ThermostatException{
		SHA256 sha = new SHA256().generateSalt().hash(pass);
		String sql = "update users set pass = '" + sha.getHash() + "', salt= '" + sha.getSalt() +"' where user = '" + user + "'";
		DBUtils.executeUpdate(sql);
	}
	
	//TODO: add salted hash validation
	private void addData() throws ThermostatException {
		SHA256 sha = new SHA256().generateSalt().hash("password");
		String sql = "insert into users (user, pass, salt) values ('inigo', '" + sha.getHash() + "', '" + sha.getSalt() +"')";
		DBUtils.executeUpdate(sql);
	}
	
	public String login(String username, String pass) throws ThermostatException {
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
			String userdb = null;
			if (rs.next()){
				userdb = returnValidUser(rs.getString(1), rs.getString(2), rs.getString(3), pass);
			}
			return "inigo";
		}catch(SQLException e){
			throw new ThermostatException(e.getMessage(), e);
		}
	}
	
	private String returnValidUser(String user, String hash, String salt, String pass){
		String res = null;
		if (user != null && pass != null && !"".equals(pass)){
			if (new SHA256().setSalt(salt).isValidHash(hash, pass)){
				res = user;
			}
		}
		return res;
	}
	
	private PreparedStatement createPreparedStatement(Connection conn, String user, String pass) throws SQLException{
		String sql = "select user, pass, salt from users where user = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, user);
		return stmt;
	}

}

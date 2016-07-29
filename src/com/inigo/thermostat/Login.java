package com.inigo.thermostat;

public class Login {
	public boolean isError;
	public String user;

	public String login(String username, String pass) {
		isError = !("inigo".equals(username) && "password".equals(pass));
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

}

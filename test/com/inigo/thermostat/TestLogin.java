package com.inigo.thermostat;

import com.inigo.domotik.db.dao.UserManager;
import com.inigo.domotik.exceptions.ThermostatException;

import junit.framework.TestCase;

public class TestLogin extends TestCase{
	
	public void testUserName() throws ThermostatException{
		UserManager login = new UserManager(null);
		login.createTable();
		assertEquals("site/index", login.login("inigo", "password"));
		assertEquals(false, login.isError);
	}
	
	public void testErroneusLogin() throws ThermostatException{
		UserManager login = new UserManager(null);
		assertEquals("login.jsp", login.login("inigo", "none"));
		assertEquals(true, login.isError);
		assertEquals("login.jsp", login.login(null, null));
		assertEquals(false, login.isError);
	}
	
}

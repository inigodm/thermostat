package com.inigo.thermostat;

import com.inigo.thermostat.exceptions.ThermostatException;

import junit.framework.TestCase;

public class TestLogin extends TestCase{
	
	public void testUserName() throws ThermostatException{
		Login login = new Login();
		assertEquals("site/index", login.login("inigo", "password"));
		assertEquals(false, login.isError);
	}
	
	public void testErroneusLogin() throws ThermostatException{
		Login login = new Login();
		assertEquals("login.jsp", login.login("inigo", "none"));
		assertEquals(true, login.isError);
		assertEquals("login.jsp", login.login(null, null));
		assertEquals(false, login.isError);
	}
	
}

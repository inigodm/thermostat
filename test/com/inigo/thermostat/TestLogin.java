package com.inigo.thermostat;

import com.inigo.domotik.Login;
import com.inigo.domotik.exceptions.ThermostatException;

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

package com.inigo.thermostat;

import junit.framework.TestCase;

public class TestLogin extends TestCase{
	
	public void testUserName(){
		Login login = new Login();
		assertEquals("site/index", login.login("inigo", "password"));
		assertEquals(false, login.isError);
	}
	
	public void testErroneusLogin(){
		Login login = new Login();
		assertEquals("login.jsp", login.login("inigo", "none"));
		assertEquals(true, login.isError);
		assertEquals("login.jsp", login.login(null, null));
		assertEquals(false, login.isError);
	}
	
}

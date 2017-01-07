package com.inigo;

import com.inigo.domotik.utils.DateUtils;

import junit.framework.TestCase;

public class TestTime extends TestCase{
	public void testHour(){
		assertTrue(DateUtils.hourOfDayAsNumber("01:01") < DateUtils.hourOfDayAsNumber("02:01"));
		assertTrue(DateUtils.hourOfDayAsNumber("01:01") < DateUtils.hourOfDayAsNumber("01:02"));
		assertTrue(DateUtils.hourOfDayAsNumber("01:01") < DateUtils.hourOfDayAsNumber("20:59"));
		assertTrue(DateUtils.hourOfDayAsNumber("00:01") < DateUtils.hourOfDayAsNumber("02:00"));
	}
}

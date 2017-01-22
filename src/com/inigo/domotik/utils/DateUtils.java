package com.inigo.domotik.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static String[] DAYS = {"D","L","M","X","J","V","S"};
	
	public static String dayOfWeek(){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return DAYS[c.get(Calendar.DAY_OF_WEEK)-1];
	}

	public static int hourOfDayAsNumber(String hour) {
		int h = Integer.parseInt(hour.substring(0,2))*100;
		int m = Integer.parseInt(hour.substring(3));
		return h+m;
	}
	
	public static Date getNextDay(Date f){
		Calendar c = Calendar.getInstance();
		c.setTime(f);
		c.add(Calendar.DATE, 1); 
		c.set(Calendar.HOUR_OF_DAY, 0);
		return c.getTime();
	}
	
}

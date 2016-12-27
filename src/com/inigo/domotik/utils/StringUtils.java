package com.inigo.domotik.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

	static DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public static Date stringToDate(String date) throws ParseException{
		return df.parse(date);
	}
	
	public static Date stringToDate(String date, String format) throws ParseException{
		DateFormat dfaux = new SimpleDateFormat(format);
		return dfaux.parse(date);
	}
}

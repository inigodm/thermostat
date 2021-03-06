package com.inigo.domotik.utils;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.inigo.domotik.db.models.Log;

public class LogManager {
	static final DateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	public static void addLogger(Log log) {
		try {
			String filename = getFileName(new Date());
		    Files.write(Paths.get(filename), log.toString().getBytes(), findMode(filename));
		}catch (Exception e) {
		    System.out.println("IOException writing logs: " + e.getMessage());
		    e.printStackTrace();
		}
	}
	
	public static String getFileName(Date date){
		return "/home/tomcat7/thermostatLog"+df.format(date)+".log";
	}
	
	private static StandardOpenOption findMode(String filename){
		if (Files.exists(Paths.get(filename), LinkOption.NOFOLLOW_LINKS)){
			return StandardOpenOption.APPEND;
		}else{
			return StandardOpenOption.CREATE;
		}
	}
	
}

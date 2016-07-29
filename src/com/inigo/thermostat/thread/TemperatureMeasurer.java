package com.inigo.thermostat.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.inigo.thermostat.thread.readers.Reader;
import com.inigo.thermostat.thread.readers.linux.CPUTempReader;
import com.inigo.thermostat.thread.readers.linux.RoomTempReader;

public class TemperatureMeasurer {
	
	public static final int TEMP_CPU_INDEX = 0;
	public static final int TEMP_ROOM_INDEX = 1;
	static Map<Integer, Reader> readers = new HashMap<>();
	static final List<String> rawTemps = new ArrayList<>();
	static ScheduledExecutorService executor = null;
	
	static {
		readers.put(TEMP_CPU_INDEX, new CPUTempReader());
		readers.put(TEMP_ROOM_INDEX, new RoomTempReader());
	}
	
	public void start(){
		if (executor == null){
			initExecutor();
		}
	}	
	
	private synchronized void initExecutor(){
		if (executor == null){
			executor = Executors.newSingleThreadScheduledExecutor();
			executor.scheduleAtFixedRate(new TempMonitoring(), 0, 10, TimeUnit.SECONDS);
		}
	}
	
	public void stop(){
		executor.shutdown();
		executor = null;
	}
	
	public List<String> getTemps(){
		return rawTemps;
	}
	
	public void setRawTemp(int index){
		String temp = readers.get(index).read();
		if (temp != ""){
			rawTemps.add(index, (new Double(temp)/1000 + ""));
		}
		System.out.println("readed " + rawTemps);
	}
	
	class TempMonitoring implements Runnable{
		@Override
		public void run() {
			rawTemps.clear();
			setRawTemp(TEMP_CPU_INDEX);
			setRawTemp(TEMP_ROOM_INDEX);
		}
	}
}

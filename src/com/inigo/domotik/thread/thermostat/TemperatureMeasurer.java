package com.inigo.domotik.thread.thermostat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.inigo.domotik.thread.Starter;
import com.inigo.domotik.thread.readers.Reader;
import com.inigo.domotik.thread.readers.thermostat.linux.CPUTempReader;
import com.inigo.domotik.thread.readers.thermostat.linux.RoomTempReader;

public class TemperatureMeasurer implements Starter{
	
	public static final int TEMP_CPU_INDEX = 0;
	public static final int TEMP_ROOM_INDEX = 1;
	static Map<Integer, Reader> readers = new HashMap<>();
	public static final List<String> rawTemps = new ArrayList<>();
	static ScheduledExecutorService executor = null;
	static int desiredTemp = 25;
	
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
	
	public static int getTemp(int tempIndex) {
		return (int)Double.parseDouble(rawTemps.get(tempIndex));
	}

    private static synchronized void activateCalefactor(){
		try {
			if (isActive()){
				Runtime.getRuntime().exec("gpio write 25 0");//enciende
			}else{
				Runtime.getRuntime().exec("gpio write 25 1");//apaga
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isActive(){
		return (getTemp(TEMP_CPU_INDEX) < desiredTemp);
	}

	public static int getDesiredTemp() {
		return desiredTemp;
	}

	public static void setDesiredTemp(int desiredTemp) {
		System.out.println("Set temp to" + desiredTemp);
		TemperatureMeasurer.desiredTemp = desiredTemp;
		activateCalefactor();
	}
	
	class TempMonitoring implements Runnable{
		@Override
		public void run() {
			while(true){
				rawTemps.clear();
				try {
					TimeUnit.MINUTES.sleep(1);
					setRawTemp(TEMP_CPU_INDEX);
					setRawTemp(TEMP_ROOM_INDEX);
					activateCalefactor();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

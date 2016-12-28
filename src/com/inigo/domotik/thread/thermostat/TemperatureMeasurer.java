package com.inigo.domotik.thread.thermostat;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.inigo.domotik.thermostat.db.LogManager;
import com.inigo.domotik.thermostat.models.Log;
import com.inigo.domotik.thread.Starter;
import com.inigo.domotik.thread.readers.Reader;
import com.inigo.domotik.thread.readers.thermostat.linux.CPUTempReader;
import com.inigo.domotik.thread.readers.thermostat.linux.RoomTempReader;

public class TemperatureMeasurer implements Starter{
	
	public static final int TEMP_CPU_INDEX = 0;
	public static final int TEMP_ROOM_INDEX = 0;
	Map<Integer, Reader> readers = new HashMap<>();
	public final List<String> rawTemps = new ArrayList<>();
	ScheduledExecutorService executor = null;
	int desiredTemp = 20;
	final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	static TemperatureMeasurer inner;
	
	private TemperatureMeasurer(){
		//readers.put(TEMP_CPU_INDEX, new CPUTempReader());
		readers.put(TEMP_ROOM_INDEX, new RoomTempReader());
	}
	
	public static TemperatureMeasurer getInstance(){
		if (inner == null){
			inner = new TemperatureMeasurer();
		}
		return inner;
	}
	
	public void start(){
		if (executor == null){
			initExecutor();
		}
	}	
	
	private synchronized void initExecutor(){
		if (executor == null || executor.isTerminated()){
			executor = Executors.newSingleThreadScheduledExecutor();
			executor.scheduleAtFixedRate(new TempMonitoring(), 0, 10, TimeUnit.SECONDS);
		}
	}
	
	public void stop(){
		executor.shutdownNow();
		System.out.println("EXECUTOR STOPPED: " + executor.isTerminated());
	}
	
	public List<String> getTemps(){
		return rawTemps;
	}
	
	public void setRawTemp(int index){
		String temp = readers.get(index).read();
		System.out.println("Raw read: " + temp);
		if (temp != ""){
			rawTemps.add(index, (new Double(temp)/1000 + ""));
		}
		System.out.println("readed " + rawTemps);
	}
	
	public int getTemp(int tempIndex) {
		return (int)Double.parseDouble(rawTemps.get(tempIndex));
	}
	
	public double getRawTemp(int tempIndex) {
		return Double.parseDouble(rawTemps.get(tempIndex));
	}

    private synchronized void activateCalefactor(){
		try {
			System.out.println("Set calefactor to on? " +isActive());
			if (isActive()){
				Runtime.getRuntime().exec("gpio write 25 0");//enciende
			}else{
				Runtime.getRuntime().exec("gpio write 25 1");//apaga
			}
		} catch (IOException e) {
			System.err.println("No se ha podido activar/desactivar el thermostato: estamos en local?");
		}
	}
	
	public boolean isActive(){
		System.out.println("Is " + this.getRawTemp(TemperatureMeasurer.TEMP_ROOM_INDEX) + "<" + desiredTemp + "?");
		return this.getTemp(TemperatureMeasurer.TEMP_ROOM_INDEX) < desiredTemp;
	}

	public int getDesiredTemp() {
		return desiredTemp;
	}

	public void setDesiredTemp(int desiredTemp) {
		System.out.println("Set temp to" + desiredTemp);
		this.desiredTemp = desiredTemp;
		activateCalefactor();
	}
	
	
	public void logNow(){
		LogManager.addLogger(buildLog());
	}
	
	public Log buildLog(){
		Log l = new Log();
		l.setDate(df.format(new Date()));
		l.setDesiredTemp(desiredTemp);
		l.setTemperature(this.getRawTemp(TemperatureMeasurer.TEMP_ROOM_INDEX));
		l.setActive(isActive()?1:0);
		return l;
	}
	
	class TempMonitoring implements Runnable{
		@Override
		public void run() {
			while(true){
				rawTemps.clear();
				try {
					System.out.println("STARTING temp measurement");
					setRawTemp(TEMP_ROOM_INDEX);
					System.out.println("END temp measurement");
					activateCalefactor();
					LogManager.addLogger(buildLog());
					TimeUnit.SECONDS.sleep(60);
				} catch (Exception e) {
					System.out.println("Error en el thread que lee temperaturas: " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
}

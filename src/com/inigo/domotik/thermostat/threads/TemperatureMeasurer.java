package com.inigo.domotik.thermostat.threads;

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

import com.inigo.domotik.db.models.Log;
import com.inigo.domotik.exceptions.ThermostatException;
import com.inigo.domotik.thermostat.db.ThermostatDAO;
import com.inigo.domotik.thermostat.servlets.models.ThermostatInfo;
import com.inigo.domotik.thermostat.threads.readers.CPUTempReader;
import com.inigo.domotik.thermostat.threads.readers.RoomTempReader;
import com.inigo.domotik.thermostat.threads.readers.YahooWeatherReader;
import com.inigo.domotik.thread.Starter;
import com.inigo.domotik.thread.readers.Reader;
import com.inigo.domotik.utils.LogManager;
import com.inigo.domotik.utils.ThermostatProperties;

//TODO: this class does too many things
public class TemperatureMeasurer implements Starter{
	public static final int DEFAULT_TEMP = 16;
	public static final int TEMP_CPU_INDEX = 0;
	public static final int TEMP_ROOM_INDEX = 0;
	public static final int TEMP_OUTSIDE_INDEX = 1;
	public static final YahooWeatherReader yahooReader = new YahooWeatherReader();
	Map<Integer, Reader> readers = new HashMap<>();
	public final List<String> rawTemps = new ArrayList<>();
	ScheduledExecutorService executor = null;
	final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	static TemperatureMeasurer inner;
	private TemperatureMeasurer(){
		readers.put(TEMP_CPU_INDEX, new CPUTempReader());
		readers.put(TEMP_ROOM_INDEX, new RoomTempReader());
		readers.put(TEMP_OUTSIDE_INDEX, new YahooWeatherReader());
		
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
	
	public void addMeasure(int index){
		String temp = readers.get(index).read();
		System.out.println("Raw read " + index + ": " + temp);
		if (temp != ""){
			rawTemps.add(index, temp);
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
	
	
	public void logNow() throws ThermostatException{
		LogManager.addLogger(buildLog());
	}
	
	public Log buildLog() throws ThermostatException{
		Log l = new Log();
		l.setDate(df.format(new Date()));
		l.setDesiredTemp(desiredTemp);
		l.setTemperature(this.getRawTemp(TemperatureMeasurer.TEMP_ROOM_INDEX));
		l.setActive(isActive()?1:0);
		l.setOutsideTemp(this.getRawTemp(TemperatureMeasurer.TEMP_OUTSIDE_INDEX));
		return l;
	}
	
	public ThermostatInfo increase(int i) {
		ThermostatInfo info = new ThermostatInfo();
		setDesiredTemp(getDesiredTemp() + i);
		info.setOutsideTemp(""+ getRawTemp(TemperatureMeasurer.TEMP_OUTSIDE_INDEX));
		info.setDesiredTemp("" + getDesiredTemp());
		info.setRoomTemp(""+ getRawTemp(TemperatureMeasurer.TEMP_ROOM_INDEX));
		info.setOn(isActive());
		return info;
	}
	
	private static int HILO = 0;
	int desiredTemp = DEFAULT_TEMP;
	boolean isInSchedule = false;
	
	private void setDesiredTempFromScheduler() throws ThermostatException {
		ThermostatDAO sm = new ThermostatDAO();
		if (sm.isNowScheludedDateTime()){
			desiredTemp = sm.getCurrentDesiredTemp();
			isInSchedule=true;
		}else if (isInSchedule){
			desiredTemp = DEFAULT_TEMP;
			isInSchedule=false;
		}
	}
	
	class TempMonitoring implements Runnable{
		@Override
		public void run() {
			HILO ++;
			while(true){
				rawTemps.clear();
				try {
					System.out.println("STARTING temp measurement in thread" + HILO + " " + this.hashCode());
					addMeasure(TEMP_ROOM_INDEX);
					addMeasure(TEMP_OUTSIDE_INDEX);
					setDesiredTempFromScheduler();
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

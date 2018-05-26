package com.inigo.domotik.thermostat.threads.readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.inigo.domotik.thread.readers.Reader;

public class RoomTempReader implements Reader {
	// this is the file in which my sensor writes the temperature that it measures
	String file = "/sys/bus/w1/devices/28-0415a4ddf9ff/w1_slave";

	public RoomTempReader() {
	}

	public RoomTempReader(String file) {
		this.file = file;
	}

	@Override
	public String read() {
		String res = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(
					new File(file))));
			String line;
			while ((line = br.readLine()) != null){
				if (line.contains("t=")){
					res = line.substring(line.indexOf("t=")+2);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return transformReaded(res);
	}

	/** Temperature must be divided by 1000 because of the format in which is readed
	 * @param temp
	 * @return
	 */
	private String transformReaded(String temp) {
		try{
			return new Double(temp)/1000 + "";
		}catch (Exception e) {
			return temp;
		}
	}
}

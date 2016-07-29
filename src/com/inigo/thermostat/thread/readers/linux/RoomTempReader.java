package com.inigo.thermostat.thread.readers.linux;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.inigo.thermostat.thread.readers.Reader;

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
					res = line.substring(2);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return res;
	}

}

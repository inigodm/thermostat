package com.inigo.domotik.thread.readers.linux;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.inigo.domotik.thread.readers.Reader;

public class CPUTempReader implements Reader {
	// this is the file in which raspbian saves the temperature of the CPU
	String file = "/sys/class/thermal/thermal_zone0/temp";
	
	public CPUTempReader() {
	}

	public CPUTempReader(String file) {
		this.file = file;
	}

	
	@Override
	public String read() {
		String res = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(
					new File("/sys/class/thermal/thermal_zone0/temp"))));
			res = br.readLine();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

}

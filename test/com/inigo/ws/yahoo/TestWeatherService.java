package com.inigo.ws.yahoo;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import com.inigo.domotik.thermostat.threads.readers.YahooWeatherReader;

public class TestWeatherService {
	public void testYahooWS() {
		YahooWeatherReader reader = new YahooWeatherReader();
		Map<String, Map<String, String>> res = reader.read("arkaia");
		assertNotNull(res.get("condition").get("temp"));
	}
}

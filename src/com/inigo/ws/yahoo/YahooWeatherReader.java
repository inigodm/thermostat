package com.inigo.ws.yahoo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class YahooWeatherReader {
	
	public <T> T read(String cityName) {
		try {
			HttpsURLConnection conn = (HttpsURLConnection) connectTo(
					"https://query.yahooapis.com/v1/public/yql?q=" 
					+ getParameters(cityName) + "&format=json");
			String response = readResponse(new InputStreamReader(conn.getInputStream()));
			response = extractDesiredNode(response);
			return parseJsonTo(response, new TypeToken<Map<String, Map<String, String>>>(){}.getType());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
//"https://query.yahooapis.com/v1/public/yql?q=select%20item.condition%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text=%27arkaia%27)%20and%20u=%27c%27&format=json"
	private String getParameters(String cityName) {
		return URLEncoder.encode("select item.condition from weather.forecast where woeid in (select woeid from geo.places(1) where text='" + cityName + "') and u = 'c'");
	}
	
	private URLConnection connectTo(String url) throws IOException {
		URL ur = new URL(url);
		return ur.openConnection();
	}
	
	private String readResponse(InputStreamReader r) throws IOException {
		char[] response = new char[2408];
		StringBuilder sb = new  StringBuilder();
		while(r.read(response) >= 0) {
			sb.append(response);
		}
		sb.append(response);
		return sb.toString();
	}
	
	private String extractDesiredNode(String res){
		return res.substring(res.indexOf("{\"condition\":"), res.indexOf("}}", res.indexOf("{\"condition\":")) + 2);
	}
	
	private <T> T parseJsonTo(String json, Type t) {
		Gson gson = new Gson();
		return gson.fromJson(json, t);
	}
}

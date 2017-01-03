package com.inigo.domotik.rest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.inigo.domotik.thermostat.models.Log;
import com.inigo.domotik.thermostat.models.LogRequest;
import com.inigo.domotik.utils.StringUtils;

@WebServlet("/site/rest/stats/get/*")
public class LogServlet extends RESTServlet<String>{

	public LogServlet() {
		super(String.class);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected Object get(List<String> pathP, Map<String, String> queryP, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String from = pathP.get(0);
		String to = pathP.get(1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
		LogRequest lr = new LogRequest()
				.setFromDate(from)
				.setToDate(to);
		try {
			DataBuilder db = new DataBuilder(df.parse(from), df.parse(to), 20);
			db.readFile();
			return (db.res+"").replaceAll("'", "\"");
		} catch (ParseException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		return null;
	}
}

class DataBuilder{
	int count = 0;
	int period;
	Date from;
	Date to;
	Gson gson = new Gson();
	public List<String> res = new ArrayList<>();
	public DataBuilder(Date from, Date to, int total){
		period = (int)(((to.getTime() - from.getTime())/(1000*60*total)));
		this.from = from;
		this.to = to;
	}	
	
	public void readFile(){
		final DateFormat df = new SimpleDateFormat("yyyyMMdd");
		try (Stream<String> stream = Files.lines(Paths.get("/home/tomcat7/thermostatLog"+df.format(from)+".log"))) {
			stream.forEach(this::fileConsumer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private void fileConsumer(String line){
		try {
			Log l = gson.fromJson(line, Log.class);
			if (StringUtils.stringToDate(l.getDate(), "yyyy-MM-dd'T'HH:mm:ss").getTime() > from.getTime()){
				count++;
				if (count >= period){
					count = 0;
					res.add(line);
					System.out.println("add " + line);
				}else{
					System.out.println("omit " + count);
				}
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


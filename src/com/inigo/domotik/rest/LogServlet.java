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
import java.util.stream.Stream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.catalina.tribes.tipis.Streamable;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.inigo.domotik.thermostat.db.LogManager;
import com.inigo.domotik.thermostat.models.Log;
import com.inigo.domotik.thermostat.models.LogRequest;
import com.inigo.domotik.utils.StringUtils;

@Path("/stats/get")
public class LogServlet {

	@GET
	@Path("/{fromDate}/{toDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object get(@PathParam("fromDate")String from, @PathParam("toDate")String to) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
		LogRequest lr = new LogRequest()
				.setFromDate(from)
				.setToDate(to);
		DataBuilder db = new DataBuilder(df.parse(from), df.parse(to), 10);
		db.readFile();
		return (db.res+"").replaceAll("'", "\"");
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
			if (StringUtils.stringToDate(l.getDate(), "MM/dd/yyyy HH:mm:ss").getTime() > from.getTime()){
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


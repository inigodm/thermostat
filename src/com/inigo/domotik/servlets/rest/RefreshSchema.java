package com.inigo.domotik.servlets.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.reflections.Reflections;

import com.inigo.domotik.db.managers.TableManager;
import com.inigo.domotik.exceptions.ThermostatException;

@WebServlet("/refresh")
public class RefreshSchema  extends RESTServlet<String>{

	public RefreshSchema() {
		super(String.class);
		// TODO Auto-generated constructor stub
	}
	
	public RefreshSchema(Class<String> in) {
		super(in);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected Object get(List<String> pathP, Map<String, String> queryP,  HttpServletRequest request, HttpServletResponse response) throws IOException{
		 Reflections reflections = new Reflections("com.inigo.domotik.db.managers");
		 try {
			Set<Class<? extends TableManager>> allClasses = reflections.getSubTypesOf(TableManager.class);
			for (Class<? extends TableManager> c : allClasses){
				 if (!c.isInterface()){
					 TableManager tm = c.newInstance();
					 tm.createTable();
				 }
			 }
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ThermostatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return "Done";
	}
}

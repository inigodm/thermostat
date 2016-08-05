package com.inigo.domotik.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.inigo.domotik.thread.Starter;
import com.inigo.domotik.thread.thermostat.TemperatureMeasurer;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {
	private static final List<Starter> starters = new ArrayList<>();
	static{
		starters.add(new TemperatureMeasurer());
	}
	
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	for (Starter s :  starters){
    		s.stop();
    	}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	for (Starter s :  starters){
    		s.start();
    	}
    }
	
}

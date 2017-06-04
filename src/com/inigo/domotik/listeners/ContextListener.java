package com.inigo.domotik.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.inigo.domotik.thermostat.threads.TemperatureMeasurer;
import com.inigo.domotik.thread.Starter;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {
	private static final List<Starter> starters = new ArrayList<>();
	static{
		starters.add(TemperatureMeasurer.getInstance());
	}
	
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	for (Starter s :  starters){
    		s.stop();
    	}
    	System.out.println("Stop context");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("Init context");
    	for (Starter s :  starters){
    		s.start();
    	}
    }
	
}

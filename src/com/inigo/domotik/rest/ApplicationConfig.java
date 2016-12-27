package com.inigo.domotik.rest;
 
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
 
public class ApplicationConfig extends ResourceConfig 
{
    public ApplicationConfig() 
    {
        packages("com.inigo.domotik.rest");
        register(LoggingFilter.class);
        register(GsonMessageBodyHandler.class);
    }
}
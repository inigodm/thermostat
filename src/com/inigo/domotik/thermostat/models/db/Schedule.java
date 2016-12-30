package com.inigo.domotik.thermostat.models.db;

public class Schedule {
	/**
	 * SCHEDULEID INTEGER PRIMARY KEY     NOT NULL," +
                " USERID           INTEGER    NOT NULL, " + 
                " FROMDATE           INTEGER    NOT NULL, " + 
                " TODATE           INTEGER    NOT NULL, " + 
                " STARTHOUR           INTEGER    NOT NULL, " + 
                " ENDHOUR           INTEGER    NOT NULL, " + 
                " MINTEMP           INTEGER    NOT NULL, " + 
                " MAXTEMP          INTEGER    NOT NULL
	 */
	private int id;
	private int active;
	private String fromDate;
	private String toDate;
	private String minHour;
	private String maxHour;
	private int desiredTemp;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int userId) {
		this.active = userId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getMinHour() {
		return minHour;
	}
	public void setMinHour(String minHour) {
		this.minHour = minHour;
	}
	public String getMaxHour() {
		return maxHour;
	}
	public void setMaxHour(String maxHour) {
		this.maxHour = maxHour;
	}
	public int getDesiredTemp() {
		return desiredTemp;
	}
	public void setDesiredTemp(int desiredTemp) {
		this.desiredTemp = desiredTemp;
	}
}

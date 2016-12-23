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
	private long fromDate;
	private long toDate;
	private int minHour;
	private int maxHour;
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
	public long getFromDate() {
		return fromDate;
	}
	public void setFromDate(long fromDate) {
		this.fromDate = fromDate;
	}
	public long getToDate() {
		return toDate;
	}
	public void setToDate(long toDate) {
		this.toDate = toDate;
	}
	public int getMinHour() {
		return minHour;
	}
	public void setMinHour(int minHour) {
		this.minHour = minHour;
	}
	public int getMaxHour() {
		return maxHour;
	}
	public void setMaxHour(int maxHour) {
		this.maxHour = maxHour;
	}
	public int getDesiredTemp() {
		return desiredTemp;
	}
	public void setDesiredTemp(int desiredTemp) {
		this.desiredTemp = desiredTemp;
	}
}

package com.inigo.domotik.db.models;

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
	private String weekdays;
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
	public String getWeekdays() {
		return weekdays;
	}
	public void setWeekdays(String weekdays) {
		this.weekdays = weekdays;
	}
	@Override
	public String toString() {
		return "Schedule [id=" + id + ", active=" + active + ", weekdays=" + weekdays + ", minHour=" + minHour
				+ ", maxHour=" + maxHour + ", desiredTemp=" + desiredTemp + "]";
	}
}

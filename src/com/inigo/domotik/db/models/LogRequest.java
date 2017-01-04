package com.inigo.domotik.db.models;

import java.text.ParseException;
import java.util.Date;

import com.inigo.domotik.utils.StringUtils;

public class LogRequest {
	public String fromDate;
	public String toDate;
	public String fromHour;
	public String toHour;
	
	public String getFromDate() {
		return fromDate;
	}
	public LogRequest setFromDate(String fromDate) {
		this.fromDate = fromDate;
		return this;
	}
	public String getToDate() {
		return toDate;
	}
	public LogRequest setToDate(String toDate) {
		this.toDate = toDate;
		return this;
	}
	public String getFromHour() {
		return fromHour;
	}
	public LogRequest setFromHour(String fromHour) {
		this.fromHour = fromHour;
		return this;
	}
	public String getToHour() {
		return toHour;
	}
	public LogRequest setToHour(String toHour) {
		this.toHour = toHour;
		return this;
	}
	public Date obtainFrom() throws ParseException{
		return StringUtils.stringToDate(fromDate);
	}
	public Date obtainTo() throws ParseException{
		return StringUtils.stringToDate(toDate);
	}
	@Override
	public String toString() {
		return "LogRequest [fromDate=" + fromDate + ", toDate=" + toDate + ", fromHour=" + fromHour + ", toHour="
				+ toHour + "]";
	}
	
}

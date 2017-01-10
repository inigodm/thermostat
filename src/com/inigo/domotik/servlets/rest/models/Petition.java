package com.inigo.domotik.servlets.rest.models;

public class Petition<T> {
	String method;
	T data;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}

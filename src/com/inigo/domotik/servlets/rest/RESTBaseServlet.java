package com.inigo.domotik.servlets.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public abstract class RESTBaseServlet<T>  extends HttpServlet{
	Class<T> in;
	Gson gson = new Gson();
	public RESTBaseServlet(Class<T> in){
		this.in = in;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> queryP = new HashMap<>();
		List<String> pathP = new ArrayList<>();
		ParamObtainer po = new ParamObtainer();
		pathP = po.fillWithPathParams(request);
		queryP = po.fillWithQueryParams(request);
		Object obj = get(pathP, queryP, request, response);
		if (obj != null){
			if (obj instanceof String){
				response.getWriter().write(obj.toString());
			}else{
				response.getWriter().write(gson.toJson(obj));
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        T reqObject = gson.fromJson(br, in);
        Object obj = post(reqObject, request, response);
		if (obj != null){
			if (obj instanceof String){
				response.getWriter().write(obj.toString());
			}else{
				response.getWriter().write(gson.toJson(obj));
			}
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        T reqObject = gson.fromJson(br, in);
        Object obj = put(reqObject, request, response);
		if (obj != null){
			if (obj instanceof String){
				response.getWriter().write(obj.toString());
			}else{
				response.getWriter().write(gson.toJson(obj));
			}
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, String> queryP = new HashMap<>();
		List<String> pathP = new ArrayList<>();
		ParamObtainer po = new ParamObtainer();
		pathP = po.fillWithPathParams(request);
		queryP = po.fillWithQueryParams(request);
		Object obj = delete(pathP, queryP, request, response);
		if (obj != null){
			if (obj instanceof String){
				response.getWriter().write(obj.toString());
			}else{
				response.getWriter().write(gson.toJson(obj));
			}
		}
	}
	
	protected abstract Object get(List<String> pathP, Map<String, String> queryP,  HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract Object post(T reqObject, HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract Object put(T reqObject, HttpServletRequest request, HttpServletResponse response) throws IOException;
	protected abstract Object delete(List<String> pathP, Map<String, String> queryP, HttpServletRequest request,HttpServletResponse response) throws IOException;
	
}


class ParamObtainer{
	public Map<String, String> fillWithQueryParams(HttpServletRequest request){
		Map<String, String> queryP = new HashMap<>();
		String name;
		while (request.getParameterNames().hasMoreElements()){
			name = request.getParameterNames().nextElement();
			queryP.put(name, request.getParameter(name));
		}
		return queryP;
	}
	
	public List<String> fillWithPathParams(HttpServletRequest request){
		String pathInfo = request.getPathInfo();
		if (pathInfo == null){
			return new ArrayList<String>();
		}
		if (pathInfo.startsWith("/")){
			pathInfo = pathInfo.substring(1);
		}
		return Arrays.asList(pathInfo.split("/"));
	}
}


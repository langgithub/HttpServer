package com.lang.httpserver;

import java.util.HashMap;
import java.util.Map;

public class servletContenx {

	//一个login-->一个loginServlet
	private Map<String,String> servlet;
	//一个log-->一个login
	private Map<String,String> mapping;
	public servletContenx() {
		// TODO Auto-generated constructor stub
		servlet=new HashMap<String,String>();
		mapping=new HashMap<String,String>();
		
	}

	public Map<String, String> getServlet() {
		return servlet;
	}

	public void setServlet(Map<String, String> servlet) {
		this.servlet = servlet;
	}

	public Map<String, String> getMapping() {
		return mapping;
	}
	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}
	
}

package com.lang.httpserver;

import java.util.ArrayList;
import java.util.List;

/**
 * <servlet-mapping>
         <servlet-name>login</servlet-name>
         <url-pattern>/*</url-pattern>
    </servlet-mapping>
 * @author lang
 *
 */
public class Mapping {

	private String name;
	private List<String> url;
	public Mapping() {
		// TODO Auto-generated constructor stub
		url=new ArrayList<String>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getUrl() {
		return url;
	}
	public void setUrl(List<String> url) {
		this.url = url;
	}
}

package com.lang.httpserver;
/**
 *  <servlet>
         <servlet-name>login</servlet-name>
         <servlet-class>com.lang.httpserver.Loginservlet</servlet-class>
    </servlet>
 * @author lang
 *
 */
public class Entity {

	private String servletName;
	private String servletClass;
	public String getServletName() {
		return servletName;
	}
	public void setServletName(String servletName) {
		this.servletName = servletName;
	}
	public String getServletClass() {
		return servletClass;
	}
	public void setServletClass(String servletClass) {
		this.servletClass = servletClass;
	}
	
}

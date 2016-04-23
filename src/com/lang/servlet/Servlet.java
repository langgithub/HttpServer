package com.lang.servlet;

import com.lang.httpserver.Request;
import com.lang.httpserver.Response;

public abstract class Servlet {

	public void service(Request req,Response rep) throws Exception{
		this.doGet(req, rep);
		this.doPost(req, rep);
	}
	public abstract void doGet(Request req,Response rep) throws Exception;
	public abstract void doPost(Request req,Response rep) throws Exception;
	/*	封装用户操作信息
	 * public void service2(Request req,Response rep){
			String s=req.getparamter("username");
			String pwd=req.getparamter("password");
			rep.creatBody("<html><head><title>构造http相应头信息</title></head> <body> ");
			rep.creatBody("用户名："+s);
			rep.creatBody("密码："+pwd);
			rep.creatBody("</body></html>");
		}
	*/	
}

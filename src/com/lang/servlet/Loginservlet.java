package com.lang.servlet;

import com.lang.httpserver.Request;
import com.lang.httpserver.Response;

public class Loginservlet extends Servlet {

	public void doGet(Request req,Response rep){
		String name=req.getparamter("username");
		String pwd=req.getparamter("password");
		if(login(name,pwd)){
			rep.creatBody("登录成功");
		}else{
			rep.creatBody("登录失败");
		}
//		rep.creatBody("<html><head><title>构造http相应头信息</title></head> <body> ");
//		rep.creatBody("用户名："+name);
//		rep.creatBody("密码："+pwd);
//		rep.creatBody("</body></html>");
	}
	public boolean login(String name,String pwd){
		return name.equals("456")&&pwd.equals("123");
	}
	public void doPost(Request req,Response rep){}
}

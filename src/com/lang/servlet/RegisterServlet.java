package com.lang.servlet;

import com.lang.httpserver.Request;
import com.lang.httpserver.Response;

public class RegisterServlet extends Servlet {

	@Override
	public void doGet(Request req, Response rep) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doPost(Request req, Response rep) throws Exception {
		// TODO Auto-generated method stub
		String s=req.getparamter("username");
		String pwd=req.getparamter("password");
		rep.creatBody("<html><head><title>构造http相应头信息</title></head> <body> ");
		rep.creatBody("用户名："+s);
		rep.creatBody("密码："+pwd);
		rep.creatBody("</body></html>");
	}
}

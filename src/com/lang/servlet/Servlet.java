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
	/*	��װ�û�������Ϣ
	 * public void service2(Request req,Response rep){
			String s=req.getparamter("username");
			String pwd=req.getparamter("password");
			rep.creatBody("<html><head><title>����http��Ӧͷ��Ϣ</title></head> <body> ");
			rep.creatBody("�û�����"+s);
			rep.creatBody("���룺"+pwd);
			rep.creatBody("</body></html>");
		}
	*/	
}

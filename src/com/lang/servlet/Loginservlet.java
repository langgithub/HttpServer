package com.lang.servlet;

import com.lang.httpserver.Request;
import com.lang.httpserver.Response;

public class Loginservlet extends Servlet {

	public void doGet(Request req,Response rep){
		String name=req.getparamter("username");
		String pwd=req.getparamter("password");
		if(login(name,pwd)){
			rep.creatBody("��¼�ɹ�");
		}else{
			rep.creatBody("��¼ʧ��");
		}
//		rep.creatBody("<html><head><title>����http��Ӧͷ��Ϣ</title></head> <body> ");
//		rep.creatBody("�û�����"+name);
//		rep.creatBody("���룺"+pwd);
//		rep.creatBody("</body></html>");
	}
	public boolean login(String name,String pwd){
		return name.equals("456")&&pwd.equals("123");
	}
	public void doPost(Request req,Response rep){}
}

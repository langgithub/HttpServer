package com.lang.httpserver;

import java.io.IOException;
import java.net.Socket;
import com.lang.servlet.Servlet;
import com.lang.util.CloseUtil;
/**
 * 一个请求一个响应，并纳入多线程
 * @author lang
 *
 */
public class Dispatch implements Runnable{

	private Socket client;
	private Request req;//分析头信息
	private Response rep;//设置响应头信息
	
	private int code=200;
	public Dispatch(Socket client){
		this.client=client;
		try {
			req=new Request(client.getInputStream());
			rep=new Response(client.getOutputStream());
		} catch (IOException e) {
		    code=500;
		    return;
		}
	}
	
	@Override
	public void run() {
		try {
			Servlet serv=WebAPP.getServlet(req.getUrl());
			if(null==serv){
				this.code=404;//Not Found
			}else{
			    serv.service(req, rep);
			}
			rep.pushToClient(code);
		} catch (Exception e) {
			rep.pushToClient(500);//系统错误
		}
		CloseUtil.closeSocket(client);
	}

}

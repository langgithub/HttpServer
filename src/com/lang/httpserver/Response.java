package com.lang.httpserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
/**
 * http报文响应头信息构造
 * @author lang
 *
 */
public class Response {

	private static final String CRLF="\r\n";//换行
	private static final String BLANK=" ";//空格
	private StringBuilder headInfo;//响应头
	private StringBuilder text;//正文信息
	private DataOutputStream dos;
	private int len;
	public Response(){
		headInfo=new StringBuilder();
		text=new StringBuilder();
        len=0;
	}
	public Response(OutputStream is){
		this();
	    dos=new DataOutputStream(is); 
	}
	/**
	 * 响应头
	 * @param code
	 * @return
	 */
	public void pushToClient(int code){
		headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
		switch(code){
			case 200:
				headInfo.append("ok");
				break;
			case 404:
				headInfo.append("not found");
			case 500:
				headInfo.append("系统内部错误");
		}
		headInfo.append(CRLF);
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Content-Type:text/html;charset=UTF-8").append(CRLF);
		headInfo.append("Content-Length:").append(len).append(CRLF);
		headInfo.append(CRLF);
		headInfo.append(text);
		System.out.println(headInfo.toString());
		try {
			dos.writeUTF(headInfo.toString());
			dos.flush();
			dos.close();
		} catch (IOException e) {
			System.out.println("输出出错");
			e.printStackTrace();
		}

	}
	public void  creatBody(String mainbody){
		text.append(mainbody);	
		len+=mainbody.getBytes().length;
	}
	
}

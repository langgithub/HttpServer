package com.lang.httpserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
/**
 * http������Ӧͷ��Ϣ����
 * @author lang
 *
 */
public class Response {

	private static final String CRLF="\r\n";//����
	private static final String BLANK=" ";//�ո�
	private StringBuilder headInfo;//��Ӧͷ
	private StringBuilder text;//������Ϣ
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
	 * ��Ӧͷ
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
				headInfo.append("ϵͳ�ڲ�����");
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
			System.out.println("�������");
			e.printStackTrace();
		}

	}
	public void  creatBody(String mainbody){
		text.append(mainbody);	
		len+=mainbody.getBytes().length;
	}
	
}

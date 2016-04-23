package com.lang.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import com.lang.util.CloseUtil;
/**
 * �����������
 * @author lang
 *
 */
public class server{

	/**
	 * �ж��Ƿ����
	 */
	public boolean isRunning = true;
	public ServerSocket server = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    server server=new server();
	    server.start(8888);
	}

	/**
	 * ����������
	 * 
	 * @param prot
	 */
	public void start(int prot) {
		try {
			server = new ServerSocket(prot);
			this.receive();
		} catch (IOException e) {
			stop();
		}
	}

	/**
	 * �رշ�����
	 */
	public void stop() {
		isRunning = false;
		CloseUtil.closeServer(server);
	}

	/**
	 * ���ܿͻ���
	 * @param client
	 */
	public  void receive() {
       	try {
       		while(isRunning){
       		  /*�˷���������̴߳���----------��δ���
       		   *  Socket client=server.accept();
       		   new Thread(new Dispatch(client)).start();*/
				new Thread(new Dispatch(server.accept())).start();
       		}
			} catch (Exception e) {
				stop();
			}
	}
    /*	
     *������Ӧ
	public static String response() {
		String responseString = "<html><head><title>����http��Ӧͷ��Ϣ</title></head> <body> 888888</body></html>";
		StringBuilder res = new StringBuilder();
		res.append("HTTP/1.1").append(BLRF).append("200").append(BLRF).append("ok").append(CLRF);
		res.append("Date:").append(new Date()).append(CLRF);
		res.append("Content-Type:text/html;charset=UTF-8").append(CLRF);
		res.append("Content-Length:").append(responseString.toString().getBytes().length).append(CLRF);
		res.append(CLRF);
		res.append(responseString);
		return res.toString();
	}
            ���Խ�������Ӧ
	public static void receive2(Socket client) {
		Request rs = null;
		com.lang.httpserver.Response response = null;
		try {
			rs = new Request(client.getInputStream());
			response = new com.lang.httpserver.Response(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = rs.getparamter("username");
		String pwd = rs.getparamter("password");
		// System.out.println(s);
		response.creatBody("<html><head><title>����http��Ӧͷ��Ϣ</title></head> <body> ");
		response.creatBody("�û�����" + s);
		response.creatBody("���룺" + pwd);
		response.creatBody("</body></html>");
		// response.creatBody("<html><head><title>����http��Ӧͷ��Ϣ</title></head><body>
		// 888888</body></html>");
		response.pushToClient(200);
	}*/


}

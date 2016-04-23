package com.lang.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import com.lang.util.CloseUtil;
/**
 * 程序启动入口
 * @author lang
 *
 */
public class server{

	/**
	 * 判断是否出错
	 */
	public boolean isRunning = true;
	public ServerSocket server = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    server server=new server();
	    server.start(8888);
	}

	/**
	 * 开启服务器
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
	 * 关闭服务器
	 */
	public void stop() {
		isRunning = false;
		CloseUtil.closeServer(server);
	}

	/**
	 * 接受客户端
	 * @param client
	 */
	public  void receive() {
       	try {
       		while(isRunning){
       		  /*此方法会出现线程错误----------尚未解决
       		   *  Socket client=server.accept();
       		   new Thread(new Dispatch(client)).start();*/
				new Thread(new Dispatch(server.accept())).start();
       		}
			} catch (Exception e) {
				stop();
			}
	}
    /*	
     *测试响应
	public static String response() {
		String responseString = "<html><head><title>构造http相应头信息</title></head> <body> 888888</body></html>";
		StringBuilder res = new StringBuilder();
		res.append("HTTP/1.1").append(BLRF).append("200").append(BLRF).append("ok").append(CLRF);
		res.append("Date:").append(new Date()).append(CLRF);
		res.append("Content-Type:text/html;charset=UTF-8").append(CLRF);
		res.append("Content-Length:").append(responseString.toString().getBytes().length).append(CLRF);
		res.append(CLRF);
		res.append(responseString);
		return res.toString();
	}
            测试接受与响应
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
		response.creatBody("<html><head><title>构造http相应头信息</title></head> <body> ");
		response.creatBody("用户名：" + s);
		response.creatBody("密码：" + pwd);
		response.creatBody("</body></html>");
		// response.creatBody("<html><head><title>构造http相应头信息</title></head><body>
		// 888888</body></html>");
		response.pushToClient(200);
	}*/


}

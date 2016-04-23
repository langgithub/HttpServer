package com.lang.util;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * ¹Ø±ÕÁ÷
 * @author lang
 *
 */
public class CloseUtil {

	public static void closeAll(Closeable...io){
		for(Closeable temp:io){
			if(temp!=null){
				try {
					temp.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void closeSocket(Socket client){
			try {
				if(null!=client){
					client.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public static void closeServer(ServerSocket server){
		try {
			if(null!=server){
				server.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
}

package com.lang.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * ��װRequest
 * ��ȡRequest�������Ϣ����Ҫ�Ǵ��ݹ����Ĳ���
 * 1).����ʽ 
 * 2).�����ַ
 * 3).�������
 * @author lang
 *
 */

@SuppressWarnings("all")
public class Request {
  
	private String method;//���󷽷�
	private String url;//������Դ
	private Map<String,List<String>> parameterValue;//�������
	
	public static final String CRLF="\r\n";//����
	private InputStream is;
	private String requestInfo;
	
	public String getUrl() {
		return url;
	}
	public Request(){
		method="";
		url="";
		parameterValue=new HashMap<String,List<String>>();
		requestInfo="";
	}
	/**
	 * ��ʼ������
	 * @param is
	 */
	public Request(InputStream is){
		this();
		this.is=is;
		byte[] data=new byte[1024];
		try {
			int len=is.read(data);
			requestInfo=new String(data,0,len);//��ȡ���������
			System.out.println(requestInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		parseRequestInfo();
	}
	//����ͷ��Ϣ
	private void parseRequestInfo(){
		if(null==requestInfo||(requestInfo=requestInfo.trim()).equals("")){
			return;
		}
		
		String paramString="";//�����������
		//��ȡ����ʽ
		String firstLine=requestInfo.substring(0,requestInfo.indexOf(CRLF));
		int index=requestInfo.indexOf("/");
		this.method=firstLine.substring(0, index).trim();
		String urlStr=firstLine.substring(index,firstLine.indexOf("HTTP/")).trim();
		if(this.method.equals("POST")){
			this.url=urlStr;
			paramString=requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
		}else if(this.method.equals("GET")){
			if(urlStr.contains("?")){
				String[] urlarray=urlStr.split("\\?");
				this.url=urlarray[0];
				paramString=urlarray[1];
				System.out.println(url+paramString);
		}else{
				this.url=urlStr;
			}
		}
		if(paramString.equals("")){
			return ;
		}
		parseParam(paramString);//��ȡ����
	}
	/**
	 * �洢��������������
	 * @param paramStr
	 */
	public void parseParam(String paramStr){
		StringTokenizer toker=new StringTokenizer(paramStr, "&");
		while(toker.hasMoreTokens()){
			String keyvalue=toker.nextToken();
			String[] keyvalues=keyvalue.split("=");
			if(keyvalues.length==1){//���username=�������
				keyvalues=Arrays.copyOf(keyvalues,2);
				keyvalues[1]=null;
			}
			
			String key=keyvalues[0].trim();
			String value=(null==keyvalues[1])?null:keyvalues[1].trim();
			
			List<String> list=new ArrayList<>();
			list.add(value);
			if(!parameterValue.containsKey(key)){
				parameterValue.put(key,list);
			}
		}
	}
	/**
	 * �������
	 * @param values
	 * @param code
	 * @return
	 */
	public String decode(String values,String code){
		try {
			java.net.URLDecoder.decode(values,code);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ��ȡ����
	 * @param name
	 * @return
	 */
	private String[] getparamtervalues(String name){
		List<String> values=null;
		if((values=parameterValue.get(name))==null){
			return null;
			
		}else{
			return values.toArray(new String[0]);
		}
	}
	public String getparamter(String name){
		String[] values=getparamtervalues(name);
		if(null==values){
			return null;
		}
		return values[0];
	}
	
}

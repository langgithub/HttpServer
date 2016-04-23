package com.lang.httpserver;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import com.lang.servlet.Servlet;

public class WebAPP {

	private static servletContenx contenx;
	static{
		contenx=new servletContenx();
		/*不用配置信息就得手动改写代码
		 Map<String,String> mapping=contenx.getMapping();
		 mapping.put("/login", "login");
		 mapping.put("/log", "login");
		 mapping.put("/req", "request");
		 
		 Map<String,Servlet> servlet=contenx.getServlet();
		 servlet.put("login", new Loginservlet());
		 servlet.put("register", new RegisterServlet());*/
		
		try {
			SAXParserFactory parserFactory = SAXParserFactory.newInstance();
			SAXParser parer = parserFactory.newSAXParser();

			WebHandler handler = new WebHandler();
            //解析配置文件
			parer.parse(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("com/lang/httpserver/web.xml"),
					handler);
			List<Entity> listEntity = handler.getEntityList();
			List<Mapping> listMapping=handler.getMappingList();
			Map<String,String> mapping=contenx.getMapping();
			Map<String,String> servlet=contenx.getServlet();
			for (Entity entity : listEntity) {
				//System.out.println(entity.getServletName());
				servlet.put(entity.getServletName(),entity.getServletClass());
				//System.out.println(entity.getServletName()+"+++++++++++++"+entity.getServletClass());
			}
			for (Mapping mapping2 : listMapping) {
				List<String> urls=mapping2.getUrl();
				for (String string : urls) {
					mapping.put(string,mapping2.getName());
					//System.out.println(string+"================"+mapping2.getName());
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	public static Servlet getServlet(String url) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
	   // System.out.println(url);
		if((null==url)||(url=url.trim()).equals("")){
			return null;
		}
		String name=contenx.getServlet().get(contenx.getMapping().get(url));
		return (Servlet)Class.forName(name).newInstance();
	}
}

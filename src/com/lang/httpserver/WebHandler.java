package com.lang.httpserver;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * 解析指定xml文档格式方法
 * @author lang
 *
 */
public class WebHandler extends DefaultHandler {

	private List<Entity> entityList;
	private String beginTag;
	private boolean isEntity;
	private Entity entity;
	private Mapping mapping;

	public List<Entity> getEntityList() {
		return entityList;
	}
	
	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}
	
	private List<Mapping> mappingList;
	public List<Mapping> getMappingList() {
		return mappingList;
	}
	
	public void setMappingList(List<Mapping> mappingList) {
		this.mappingList = mappingList;
	}
	@Override
	public void startDocument() throws SAXException {
		entityList = new ArrayList<Entity>();
		mappingList = new ArrayList<Mapping>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (null != qName) {
			beginTag = qName;
			if (qName.equals("servlet")) {
				isEntity = true;
				entity = new Entity();
			} else if (qName.equals("servlet-mapping")) {
				isEntity = false;
				mapping = new Mapping();
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (beginTag != null) {
			String str = new String(ch, start, length);
			if (isEntity) {
				if (beginTag.equals("servlet-name")) {
					entity.setServletName(str);
				} else if (beginTag.equals("servlet-class")) {
					entity.setServletClass(str);
				}
			} else {
				if (beginTag.equals("servlet-name")) {
					mapping.setName(str);
				} else if (beginTag.equals("url-pattern")) {
					mapping.getUrl().add(str);
				}
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (null != qName) {
			if (qName.equals("servlet")) {
				entityList.add(entity);
			} else if (qName.equals("servlet-mapping")) {
				mappingList.add(mapping);
			}
		}
		beginTag=null;//读取空格如果不为空，会把空写进对象中
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

}

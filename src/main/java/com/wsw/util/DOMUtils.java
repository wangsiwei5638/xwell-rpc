package com.wsw.util;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

 
/**   
 * @ClassName:  DOMUtils   
 * @Description:
 * 				用于解析xml文件
 * @author: wsw
 * @date:   2019年3月16日
 * @Copyright: http://www.iwangsiwei.com
 */
@SuppressWarnings("unchecked")
public class DOMUtils {
	
	private static Document DOC ;
	
	static {
		SAXReader reader = new SAXReader();
		InputStream is = DOMUtils.class.getClassLoader().getResourceAsStream("rpc-config.xml");
		try {
			DOC = reader.read(is);
		} catch (DocumentException e) {
			
		}
	}
//	SAXReader reader = new SAXReader();
//	InputStream is = 
//			Test01.class.getClassLoader().getResourceAsStream("rpc-config.xml");
//	Document doc = reader.read(is);
//	Element rootElement = doc.getRootElement();
//	Iterator<Element> elementIterator = rootElement.elementIterator();
//	while (elementIterator.hasNext()) {
//		Element next = elementIterator.next();
//		if("url".equals(next.getName())) {
//			System.out.println(next.attribute("id").getData());
//			System.out.println(next.attribute("ip").getData());
//			System.out.println(next.attribute("port").getData());
//		}else if("protocol".equals(next.getName())) {
//			System.out.println(next.getData());
//		}
//	}
	
	/**
	 * @param e	需要查找属性的元素
	 * @return
	 */
	private static Map<String, Object> parseRPCXMLAttr(Element e) {
		AssertUtil.isNotNull(e);
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		List<Attribute> attributes = e.attributes();
		for (Attribute attribute : attributes) {
			map.put(attribute.getName(), attribute.getData());
		}
		
		return map;
	}
	
	/**
	 * @param e	递归开始的元素
	 * @param elementName	需要查找属性的元素名称
	 * @return
	 */
	public static Map<String, Object> parseRPCXMLAttr(Element e,String elementName) {
		Element selectElement = selectElement(e, elementName);
		return parseRPCXMLAttr(selectElement);
	}
	
	/**
	 * @param e	需要查找数据的元素
	 * @return
	 */
	private static Map<String, Object> parseRPCXMLData(Element e) {
		AssertUtil.isNotNull(e);
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		map.put(e.getName(), e.getData());
		return map;
	}
	
//	public static void main(String[] args) {
//		System.out.println(parseRPCXMLData("protocol"));
//	}
	
	/**
	 * @param elementName 需要查找数据的元素名称
	 * @return
	 */
	public static Map<String, Object> parseRPCXMLData(String elementName) {
		Element selectElement = selectElement(null, elementName);
		return parseRPCXMLData(selectElement);
	}
	
	private static Element selectElement(Element e,String elementName) {
		AssertUtil.isNotNull(elementName);
		Element rootElement;
		if(e == null) {
			rootElement =  DOC.getRootElement();
		}else {
			rootElement = e;
		}
		
		Iterator<Element> elementIterator = rootElement.elementIterator();
		while (elementIterator.hasNext()) {
			Element next = elementIterator.next();
			if(elementName.equals(next.getName())) {
				return next;
			}else {
				selectElement(next,elementName);
			}
		}
		return null;
	}
	
}

package com.wsw.test;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wsw.util.DOMUtils;

public class Test01 {

	public static void main(String[] args) throws Exception {
//		SAXReader reader = new SAXReader();
//		InputStream is = 
//				Test01.class.getClassLoader().getResourceAsStream("rpc-config.xml");
//		Document doc = reader.read(is);
//		Element rootElement = doc.getRootElement();
//		Iterator<Element> elementIterator = rootElement.elementIterator();
//		while (elementIterator.hasNext()) {
//			Element next = elementIterator.next();
//			if("url".equals(next.getName())) {
//				System.out.println(next.attribute("id").getData());
//				System.out.println(next.attribute("ip").getData());
//				System.out.println(next.attribute("port").getData());
//			}else if("protocol".equals(next.getName())) {
//				System.out.println(next.getData());
//			}
//			
//			
//		}
		System.out.println( DOMUtils.class.getClassLoader());
//		<company id="1001">
//        <name>万科A</name>
//        <address>广东深圳</address>
//    </company>
//    <company id="1002">
//        <name>恒大B</name>
//        <address>广东广州</address>
//    </company>
//    <company id="1003">
//        <name>金地C</name>
//        <address>北京</address>
//    </company>
//    <company id="1006">
//        <name>绿地D</name>
//        <address>上海</address>
//    </company>

	}

}

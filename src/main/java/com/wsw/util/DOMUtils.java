package com.wsw.util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

 
/**   
 * @ClassName:  DOMUtils   
 * @Description:
 * 				用于解析xml文件
 * @author: wsw
 * @date:   2019年3月16日
 * @Copyright: http://www.iwangsiwei.com
 */
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
	
	
	
	public static Map<String, Object> parseRPCXMLAttr() {
		
		return null;
	}
	
	public static Map<String, Object> parseRPCXMLData() {
		
		return null;
	}
	
	
}

package com.wsw.service.cache;

import java.util.Map;

import com.wsw.bean.URL;
import com.wsw.util.DOMUtils;

public class URLCache {
	/**   
	 * @Fields DEF_URL : 缺省的URL配置，XML配置中的第一个URL
	 */   
	private static URL DEF_URL;

	public static URL getDefUrl() {
		synchronized (URLCache.class) {
			if(DEF_URL == null) {
				Map<String, Object> urlMap = DOMUtils.parseRPCXMLAttr(null, "url");
				DEF_URL = new URL((String)urlMap.get("ip"), Integer.valueOf((String)urlMap.get("port")));
			}
		}
		
		
		return DEF_URL;
	}
	
}

package com.wsw.protocol.tcp;

import com.wsw.bean.URL;
import com.wsw.protocol.GeneralProtocol;
import com.wsw.protocol.Protocol;
import com.wsw.service.cache.URLCache;

/**   
 * @ClassName:  TCPProtocol   
 * @Description:
 * 			
 * 				基于socket的TCP协议
 * 
 * @author: wsw
 * @date:   2019年3月23日
 * @Copyright: http://www.iwangsiwei.com
 */
public class TCPProtocol extends GeneralProtocol {

	public void start(URL url) {
		new TCPServiceHandler().handle(url);

	}
	
	public void start() {
		start(URLCache.getDefUrl());
	}

}

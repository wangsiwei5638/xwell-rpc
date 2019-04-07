package com.wsw.protocol;

import java.util.Map;

import com.wsw.exception.RPCException;
import com.wsw.protocol.http.HttpProtocol;
import com.wsw.protocol.tcp.TCPProtocol;
import com.wsw.service.common.ProtocolType;
import com.wsw.service.common.RPCConstants;
import com.wsw.util.DOMUtils;

/**   
 * @ClassName:  GeneralProtocol   
 * @Description:
 * 
 * 					通用协议，作为所有协议的基类
 * 
 * @author: wsw
 * @date:   2019年3月24日
 * @Copyright: http://www.iwangsiwei.com
 */
public abstract class GeneralProtocol implements Protocol{

	
	//设置初始协议
	static {
		init();
	}
	
	
	
	/**
	 * 		启动服务
	 */
	public static void startServer() {
		
		ProtocolType protocolType = RPCConstants.getCurrentProtocol();
		switch (protocolType) {
			case HTTP:
				new HttpProtocol().start();
				break;
			case HTTPS:
				
				break;
			case NETTY:
				
				break;
			case TCP:
				new TCPProtocol().start();
				break;
			case UDP:
				
				break;
			case WEBSERVICE:
				
				break;
			default: throw new RPCException("启动"+protocolType+"协议失败");
		}
		
	}



	/**
	 * 	初始化协议
	 */
	private static void init() {
		//设置协议
		Map<String, Object> data = DOMUtils.parseRPCXMLData("protocol");
		Object o;
		String protocol;
		protocol=((o = data.get("protocol"))==null||"".equals(o.toString().trim()))?
				RPCConstants.DEF_PROTOCOL.protocolName:o.toString();
		System.setProperty("protocol", protocol);
		
		//设置IO模式
		Map<String, Object> attr = DOMUtils.parseRPCXMLAttr(null, "protocol");
		String ioType;
		ioType = ((o = attr.get("ioType"))==null||"".equals(o.toString().trim()))?
				RPCConstants.DEF_IO_TYPE.ioName:o.toString();
		System.setProperty("ioType", ioType);
		
	}
	
	
}

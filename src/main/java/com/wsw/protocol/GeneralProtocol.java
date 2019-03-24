package com.wsw.protocol;

import java.util.Map;

import com.wsw.exception.RPCException;
import com.wsw.protocol.http.HttpProtocol;
import com.wsw.protocol.tcp.TCPProtocol;
import com.wsw.service.common.ProtocolType;
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

	/**   
	 * @Fields PT : xwell-prc所支持的所有的协议类型
	 */   
	private static final ProtocolType[] PT = ProtocolType.values(); 
	/**   
	 * @Fields DEF_PROTOCOL : 缺省协议
	 */   
	private static final ProtocolType DEF_PROTOCOL = ProtocolType.HTTP; 
	
	//设置初始协议
	static {
		init();
	}
	
	
	
	/**
	 * 		启动服务
	 */
	public static void startServer() {
		
		String protocol = System.getProperty("protocol");
		ProtocolType protocolType = DEF_PROTOCOL;
		for (ProtocolType p : PT) {
			String protocolName = p.getProtocolName();
			if(protocolName.equals(protocol)) {
				protocolType = p;
				break;
			}
		}
		
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
		Map<String, Object> data = DOMUtils.parseRPCXMLData("protocol");
		Object o;
		String protocol;
		protocol=((o = data.get("protocol"))==null||"".equals(o.toString().trim()))?"http":o.toString();
		System.setProperty("protocol", protocol);
		
	}
	
}

package com.wsw.service.common;


public class RPCConstants {

	/**   
	 * @Fields CORE_POOL_SIZE : 核心线程数
	 */   
	public static final Integer CORE_POOL_SIZE = 20;
	
	/**   
	 * @Fields XWELL_RPC_DEF_PORT : xwell-rpc默认端口
	 */   
	public static final Integer XWELL_RPC_DEF_PORT = 8080;
	/**   
	 * @Fields PT : xwell-prc所支持的所有的协议类型
	 */   
	public static final ProtocolType[] PT = ProtocolType.values(); 
	/**   
	 * @Fields DEF_PROTOCOL : 缺省协议
	 */   
	public static final ProtocolType DEF_PROTOCOL = ProtocolType.HTTP; 
	/**   
	 * @Fields DEF_IO_TYPE : 默认的IO类型	（目前支持nio、bio）
	 */   
	public static final IOType DEF_IO_TYPE = IOType.NIO;
	/**
	 * @Fields NIO_BUFFER_SIZE : NIO缓冲区大小
	 */
	public static final Integer NIO_BUFFER_SIZE = 1024;


	/**
	 * 		获取当前协议类型
	 */
	public static ProtocolType getCurrentProtocol() {
		try {
			//类加载进内存 
			Class.forName("com.wsw.protocol.GeneralProtocol");
		} catch (ClassNotFoundException e) {
		}
		String protocol = System.getProperty("protocol");
		ProtocolType protocolType = DEF_PROTOCOL;
		for (ProtocolType p : PT) {
			String protocolName = p.getProtocolName();
			if(protocolName.equals(protocol)) {
				protocolType = p;
				break;
			}
		}
		return protocolType;
	}
//	private static final <T> T get(String key,Object defValue) {
//		Map<String, Object> parseRPCXMLData = DOMUtils.parseRPCXMLData(key);
//	}
}

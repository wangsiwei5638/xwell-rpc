package com.wsw.service.common;

/**   
 * @ClassName:  ProtocolType   
 * @Description:
 * 
 * 					协议类型
 * 
 * @author: wsw
 * @date:   2019年3月24日
 * @Copyright: http://www.iwangsiwei.com
 */
public enum ProtocolType {

	HTTP(1,"http"),
	HTTPS(2,"https"),
	TCP(3,"tcp"),
	UDP(4,"udp"),
	NETTY(5,"netty"),
	WEBSERVICE(6,"webservice");
	
	public Integer id;
	public String protocolName;
	
	
	private ProtocolType(Integer id, String protocolName) {
		this.id = id;
		this.protocolName = protocolName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProtocolName() {
		return protocolName;
	}
	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}
	
	
}

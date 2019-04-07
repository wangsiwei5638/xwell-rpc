package com.wsw.service.common;

/**   
 * @ClassName:  IOType   
 * @Description:
 * 
 * 					xwell-rpc框架所支持的IO类型
 * 
 * @author: wsw
 * @date:   2019年4月7日
 * @Copyright: http://www.iwangsiwei.com
 */
public enum IOType {

	BIO(1,"bio"),
	NIO(2,"nio");
//	AIO(3,"tcp")
	
	public Integer id;
	public String ioName;
	private IOType(Integer id, String ioName) {
		this.id = id;
		this.ioName = ioName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIoName() {
		return ioName;
	}
	public void setIoName(String ioName) {
		this.ioName = ioName;
	}
	
	
	
}

package com.wsw.bean;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author wsw
 *
 */
public class RPCRequest implements Serializable{

	private static final long serialVersionUID = 2434379241114505067L;
	
	//调用的接口名
	private String interfaceName;
	private String methodName;
	private Class<?>[] types;
	private Object[] params;
	
	
	public RPCRequest(String interfaceName, String methodName, Class<?>[] types, Object[] params) {
		super();
		this.interfaceName = interfaceName;
		this.methodName = methodName;
		this.types = types;
		this.params = params;
	}
	@Override
	public String toString() {
		return "RPCRequest [interfaceName=" + interfaceName + ", methodName=" + methodName + ", types="
				+ Arrays.toString(types) + ", params=" + Arrays.toString(params) + "]";
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Class<?>[] getTypes() {
		return types;
	}
	public void setTypes(Class<?>[] types) {
		this.types = types;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

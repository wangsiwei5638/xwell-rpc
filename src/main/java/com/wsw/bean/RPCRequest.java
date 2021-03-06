package com.wsw.bean;

import java.io.Serializable;
import java.util.Arrays;

import com.wsw.service.cache.URLCache;

/**   
 * @ClassName:  RPCRequest   
 * @Description:	RPC请求对象
 * @author: wsw
 * @date:   2019年3月15日
 * @Copyright: http://www.iwangsiwei.com
 */
public class RPCRequest implements Serializable{

	private static final long serialVersionUID = 2434379241114505067L;
	
	private URL url;
	/**   
	 * @Fields interfaceName : 调用的接口名 
	 */   
	private String interfaceName;
	/**   
	 * @Fields methodName : 调用远程方法名称 
	 */   
	private String methodName;
	/**   
	 * @Fields types : 方法的数据类型
	 */   
	private Class<?>[] types;
	/**   
	 * @Fields params : 调用的远程方法参数
	 */   
	private Object[] params;
	
	public RPCRequest(String interfaceName, String methodName) {
		super();
		this.interfaceName = interfaceName;
		this.methodName = methodName;
		this.url = getUrl();
	}

	public RPCRequest(String interfaceName, String methodName, Class<?>[] types) {
		super();
		this.interfaceName = interfaceName;
		this.methodName = methodName;
		this.types = types;
		this.url = getUrl();
	}
	
	public RPCRequest(String interfaceName, String methodName, Class<?>[] types, Object[] params) {
		super();
		this.interfaceName = interfaceName;
		this.methodName = methodName;
		this.types = types;
		this.params = params;
	}

	public RPCRequest(URL url, String interfaceName, String methodName, Class<?>[] types) {
		super();
		this.url = url;
		this.interfaceName = interfaceName;
		this.methodName = methodName;
		this.types = types;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((interfaceName == null) ? 0 : interfaceName.hashCode());
		result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
		result = prime * result + Arrays.hashCode(params);
		result = prime * result + Arrays.hashCode(types);
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RPCRequest other = (RPCRequest) obj;
		if (interfaceName == null) {
			if (other.interfaceName != null)
				return false;
		} else if (!interfaceName.equals(other.interfaceName))
			return false;
		if (methodName == null) {
			if (other.methodName != null)
				return false;
		} else if (!methodName.equals(other.methodName))
			return false;
		if (!Arrays.equals(params, other.params))
			return false;
		if (!Arrays.equals(types, other.types))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
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
	public URL getUrl() {
		if(url == null) {
			return URLCache.getDefUrl();
		}
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	
	
	
	

}

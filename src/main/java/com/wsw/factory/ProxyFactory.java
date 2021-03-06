package com.wsw.factory;

import com.wsw.bean.RPCRequest;

/**   
 * @ClassName:  ProxyFactory   
 * @Description:
 * 					代理工厂接口
 * 					接下来的实现类都需要实现这个工厂
 * @author: wsw
 * @date:   2019年3月15日
 * @Copyright: http://www.iwangsiwei.com
 */
public interface ProxyFactory {

	/**
	 * 	创建代理对象
	 */
	<T> T newProxy(Class<T> interfaceClass,RPCRequest rpcRequest);
}

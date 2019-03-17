package com.wsw.factory.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import com.wsw.bean.RPCRequest;
import com.wsw.bean.URL;
import com.wsw.factory.ProxyFactory;
import com.wsw.protocol.http.HttpClient;
import com.wsw.register.Register;
import com.wsw.service.cache.URLCache;
import com.wsw.util.AssertUtil;

public class ProxyFactoryImpl implements ProxyFactory{

	private static Logger logger = Logger.getLogger(ProxyFactoryImpl.class);

	@SuppressWarnings("unchecked")
	public <T> T getProxy(final Class<T> interfaceClass,final RPCRequest rpcRequest){
		
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class[] {interfaceClass}, new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				HttpClient httpClient = new HttpClient();
				rpcRequest.setParams(args);
				
				return httpClient.post(rpcRequest);
			}
			
			
		});
		
	}
	
//	public <T> T getProxy(final Class<T> interfaceClass,final RPCRequest rpcRequest){
//		return getProxy(interfaceClass, rpcRequest,URLCache.getDefUrl());
//	}
}

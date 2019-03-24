package com.wsw.factory.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import com.wsw.bean.RPCRequest;
import com.wsw.bean.URL;
import com.wsw.exception.RPCException;
import com.wsw.factory.ProxyFactory;
import com.wsw.protocol.Client;
import com.wsw.protocol.http.HttpClient;
import com.wsw.protocol.http.HttpProtocol;
import com.wsw.protocol.tcp.TCPClient;
import com.wsw.protocol.tcp.TCPProtocol;
import com.wsw.register.Register;
import com.wsw.service.cache.URLCache;
import com.wsw.service.common.ProtocolType;
import com.wsw.service.common.RPCConstants;
import com.wsw.util.AssertUtil;

public class ProxyFactoryImpl implements ProxyFactory{

	private static Logger logger = Logger.getLogger(ProxyFactoryImpl.class);
	
	private static ProxyFactory proxyFactory = new ProxyFactoryImpl();
	
	/**
	 * 	单利此类，私有构造方法
	 */
	private ProxyFactoryImpl() {
		super();
	}
	
	public static ProxyFactory getInstance(){  
        return proxyFactory;  
    } 

	public static <T> T getProxy(Class<T> interfaceClass,RPCRequest rpcRequest){
		return proxyFactory.newProxy(interfaceClass, rpcRequest);
	}

	@SuppressWarnings("unchecked")
	public <T> T newProxy(final Class<T> interfaceClass,final RPCRequest rpcRequest){
		
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class[] {interfaceClass}, new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				Client client = new HttpClient();
				ProtocolType protocolType = RPCConstants.getCurrentProtocol();
				switch (protocolType) {
				case HTTP:break;
				case HTTPS:
					break;
				case NETTY:
					break;
				case TCP:
					client = new TCPClient();
					break;
				case UDP:
					
					break;
				case WEBSERVICE:
					
					break;
				default: throw new RPCException("启动客户端"+protocolType+"协议失败");
			}
				
				
				rpcRequest.setParams(args);
				
				return client.doSend(rpcRequest);
			}
			
			
		});
		
	}
	
//	public <T> T getProxy(final Class<T> interfaceClass,final RPCRequest rpcRequest){
//		return getProxy(interfaceClass, rpcRequest,URLCache.getDefUrl());
//	}
}

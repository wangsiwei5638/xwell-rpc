package com.wsw.consumer;

import org.apache.log4j.Logger;

import com.wsw.bean.RPCRequest;
import com.wsw.bean.URL;
import com.wsw.factory.impl.ProxyFactoryImpl;
import com.wsw.protocol.http.HttpClient;
import com.wsw.provider.Hello;

public class T02 {

	private static Logger logger = Logger.getLogger(ProxyFactoryImpl.class); 
	public static void main(String[] args) {
		RPCRequest rpcRequest = new RPCRequest("Hello", "sayHello", new Class[] {String.class}, new Object[]{"wsw"});
		Hello proxy = new ProxyFactoryImpl().getProxy(Hello.class,rpcRequest,new URL("localhost", 8080));

		logger.info(proxy.sayHello("fwefewf"));
	}

}

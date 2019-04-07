package com.wsw.consumer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.wsw.bean.RPCRequest;
import com.wsw.bean.URL;
import com.wsw.factory.impl.ProxyFactoryImpl;
import com.wsw.protocol.http.HttpClient;
import com.wsw.protocol.tcp.TCPClient;
import com.wsw.provider.Hello;
import com.wsw.service.cache.URLCache;
import com.wsw.service.common.ProtocolType;

public class C {

	private static Logger logger = Logger.getLogger(ProxyFactoryImpl.class);
	public static void main(String[] args) throws Exception {
//		 http()
//		new TCPClient().doSend(
//				new RPCRequest("Hello", "sayHello", new Class[] { String.class }, new Object[] {"hhwsw"}));
		
//		Socket socket = new Socket(URLCache.getDefUrl().getHost(), URLCache.getDefUrl().getProt());
//		OutputStream outputStream = socket.getOutputStream();
//		outputStream.write(98);
//		socket.shutdownOutput();
		
		
		RPCRequest rpcRequest = new RPCRequest("Hello", "sayHello", new Class[] {String.class});
		Hello proxy = ProxyFactoryImpl.getProxy(Hello.class,rpcRequest);
		String sayHello = proxy.sayHello("wsw123");
		System.out.println(sayHello);

	}
	
	static void http() {
//		RPCRequest rpcRequest = new RPCRequest("Hello", "sayHello", new Class[] {String.class});
//		ProxyFactoryImpl proxyFactoryImpl = new ProxyFactoryImpl();
//		Hello proxy = proxyFactoryImpl.getProxy(Hello.class,rpcRequest);
//
//		logger.info(proxy.sayHello("测试"));
	}

}

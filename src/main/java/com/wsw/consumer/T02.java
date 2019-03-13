package com.wsw.consumer;

import com.wsw.bean.RPCRequest;
import com.wsw.bean.URL;
import com.wsw.factory.ProxyFactory;
import com.wsw.protocol.http.HttpClient;
import com.wsw.provider.Hello;

public class T02 {

	public static void main(String[] args) {
		
		Hello proxy = ProxyFactory.getProxy(Hello.class);
		System.out.println(proxy.sayHello("fwefewf"));

	}

}

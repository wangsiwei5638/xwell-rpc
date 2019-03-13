package com.wsw.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.wsw.bean.RPCRequest;
import com.wsw.bean.URL;
import com.wsw.protocol.http.HttpClient;
import com.wsw.register.Register;

public class ProxyFactory<T> {

	public static <T> T getProxy(final Class<T> interfaceClass){
		
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class[] {interfaceClass}, new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				HttpClient httpClient = new HttpClient();
				RPCRequest rpcRequest = new RPCRequest("Hello", "sayHello", new Class[] {String.class}, new Object[]{"wsw"});
				return httpClient.post(Register.getRegister(interfaceClass.getName()), rpcRequest);
			}
			
			
		});
		
	}
}

package com.wsw.provider.impl;

import com.wsw.annotation.RPCClass;
import com.wsw.annotation.RPCMethod;
import com.wsw.provider.Hello;

@RPCClass
public class HelloImpl implements Hello{

	@RPCMethod
	public String sayHello(String name) {
		System.out.println(name + ": hello");
		return name + "调用了服务端sayHello方法 ";
	}

}

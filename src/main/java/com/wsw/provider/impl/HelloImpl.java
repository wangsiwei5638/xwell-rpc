package com.wsw.provider.impl;

import com.wsw.provider.Hello;

public class HelloImpl implements Hello{

	public String sayHello(String name) {
		System.out.println(name + ": hello");
		return name + "调用了服务端sayHello方法 ";
	}

}

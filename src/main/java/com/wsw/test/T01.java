package com.wsw.test;

import com.wsw.bean.URL;
import com.wsw.protocol.http.HttpProtocol;
import com.wsw.provider.impl.HelloImpl;
import com.wsw.register.Register;

public class T01 {

	
	public static void main(String[] args) {
		
		URL url = new URL("localhost", 8080);
		Register.regist(url, "Hello", HelloImpl.class);
		
		HttpProtocol httpProtocol = new HttpProtocol();
		httpProtocol.start(url);
		
		
	}
}

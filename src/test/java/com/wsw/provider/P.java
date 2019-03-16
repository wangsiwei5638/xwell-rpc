package com.wsw.provider;

import com.wsw.bean.URL;
import com.wsw.protocol.http.HttpProtocol;
import com.wsw.provider.impl.HelloImpl;
import com.wsw.register.Register;

public class P {

	
	public static void main(String[] args) {
		
		URL url = new URL("localhost", 8080);
		Register.regist(url, "Hello", HelloImpl.class);
		
		HttpProtocol httpProtocol = new HttpProtocol();
		httpProtocol.start(url);
		
		
	}
}

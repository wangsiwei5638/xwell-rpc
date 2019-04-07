package com.wsw.provider;

import com.wsw.bean.URL;
import com.wsw.protocol.GeneralProtocol;
import com.wsw.protocol.http.HttpProtocol;
import com.wsw.provider.impl.HelloImpl;
import com.wsw.register.Register;
import com.wsw.util.DOMUtils;

public class P {

	
	public static void main(String[] args) {
//		http()
		Register.regist("Hello", HelloImpl.class);
		tcp();
		
	}
	
	static void tcp() {
		GeneralProtocol.startServer();
	}
	
	
	static void http() {
		HttpProtocol httpProtocol = new HttpProtocol();
		httpProtocol.start();
	}
}

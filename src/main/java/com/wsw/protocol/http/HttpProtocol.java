package com.wsw.protocol.http;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

import com.wsw.bean.URL;

public class HttpProtocol {

	public void start(String host,Integer port) {
		start(new URL(host, port)); 
	}
	
	
	
	public void start(URL url) {
		String hostName = url.getHost();
		Integer port = url.getProt();
		
		Tomcat tomcat = new Tomcat();
		
		/*參考server.xml*/
		Server server = tomcat.getServer();
		Service service = server.findService("Tomcat");
		//创建Connector
		Connector connector = new Connector();
		connector.setPort(port);
		//创建Engine
		Engine engine = new StandardEngine();
		engine.setDefaultHost(hostName);
		//创建host
		Host host = new StandardHost();
		host.setName(hostName);
		//
		String contextPath = "";
		Context context = new StandardContext();
		context.setPath(contextPath);
		context.addLifecycleListener(new Tomcat.FixContextListener());
		
		host.addChild(context);
		engine.addChild(host);
		service.setContainer(engine);
		service.addConnector(connector);
		
		tomcat.addServlet(contextPath, "dispatcherServlet", new DispatcherServlet());
		context.addServletMappingDecoded("/*", "dispatcherServlet");
		System.out.println("tomcat启动成功");
		
		try {
			tomcat.start();
			tomcat.getServer().await();
		} catch (LifecycleException e) {
			System.out.println("tomcat 启动失败");
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void main(String[] args) {
		new HttpProtocol().start("localhost", 8080);
	}
}

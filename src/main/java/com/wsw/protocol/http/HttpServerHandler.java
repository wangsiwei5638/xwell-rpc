package com.wsw.protocol.http;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.wsw.bean.RPCRequest;
import com.wsw.bean.URL;
import com.wsw.register.Register;

public class HttpServerHandler {

	
	public void handler(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(req.getInputStream());
			RPCRequest rpcRequest = (RPCRequest) objectInputStream.readObject();
			
			String interfaceName = rpcRequest.getInterfaceName();
			URL url = new URL("localhost", 8080);
			Class<?> impl = Register.getClass(url, interfaceName);
			
			Method method = impl.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
			Object invoke = method.invoke(impl.newInstance(), rpcRequest.getParams());
			
			IOUtils.write(invoke.toString(),resp.getOutputStream());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

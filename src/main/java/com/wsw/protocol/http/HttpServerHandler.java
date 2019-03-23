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
import com.wsw.protocol.InvokeMethodHandler;
import com.wsw.protocol.Server;
import com.wsw.register.Register;

public class HttpServerHandler extends InvokeMethodHandler implements Server {

	public void handle(HttpServletRequest req, HttpServletResponse resp) {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(req.getInputStream());
			RPCRequest rpcRequest = (RPCRequest) objectInputStream.readObject();

			Object obj = super.doInvoke(rpcRequest);

			IOUtils.write(obj.toString(), resp.getOutputStream());

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

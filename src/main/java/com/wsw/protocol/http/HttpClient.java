package com.wsw.protocol.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.wsw.bean.RPCRequest;
import com.wsw.bean.URL;
import com.wsw.protocol.Client;

public class HttpClient implements Client{

	private static Logger logger = Logger.getLogger(HttpClient.class);
			
	public Object doSend(RPCRequest rpcRequest) {
		return post(rpcRequest);
	}
	
	
	private Object post(RPCRequest rpcRequest) {
		ObjectOutputStream objectOutputStream = null;
		try {
			java.net.URL url = new java.net.URL("http",rpcRequest.getUrl().getHost(),rpcRequest.getUrl().getProt(),"/");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			OutputStream outputStream = connection.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(rpcRequest);
			objectOutputStream.flush();
			
			InputStream inputStream = connection.getInputStream();
			return IOUtils.toString(inputStream);
			
			
		} catch (MalformedURLException e) {
			logger.error("URL格式错误",e);
		} catch (IOException e) {
			logger.error("IO异常",e);
		} finally {
			if(objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
				}
			}
		}
		return null;

	}

}

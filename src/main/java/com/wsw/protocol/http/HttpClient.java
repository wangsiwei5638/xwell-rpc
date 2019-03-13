package com.wsw.protocol.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

import com.wsw.bean.RPCRequest;
import com.wsw.bean.URL;

public class HttpClient {

	public String post(URL u,RPCRequest rpcRequest) {
		ObjectOutputStream objectOutputStream = null;
		try {
			java.net.URL url = new java.net.URL("http",u.getHost(),u.getProt(),"/");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

package com.wsw.protocol.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import com.wsw.bean.RPCRequest;
import com.wsw.protocol.Client;
import com.wsw.service.cache.URLCache;

public class TCPClient implements Client {

	public Object doSend(RPCRequest rpcRequest) {
		// 1.创建客户端的Socket，指定服务器的IP和端口
		try {
			Socket socket = newSocket();

			// 2.获取该Socket的输出流，用来向服务器发送信息
			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			os.writeObject(rpcRequest);
			socket.shutdownOutput();

			// 3.获取输入流，取得服务器的信息
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while ((info = br.readLine()) != null) {
				System.out.println("服务器端的信息：" + info);
			}
			socket.shutdownInput();
			os.close();
			is.close();
			br.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static Socket newSocket() throws UnknownHostException, IOException {
		return new Socket(URLCache.getDefUrl().getHost(), URLCache.getDefUrl().getProt());

	}

	// 端口重复绑定问题
	public static void main(String[] args) {
		int i = 0;
		synchronized (args) {
			while (true) {
				new TCPClient().doSend(
						new RPCRequest("Hello", "sayHello", new Class[] { String.class }, new Object[] { i++ + "" }));
			}

		}

	}

}

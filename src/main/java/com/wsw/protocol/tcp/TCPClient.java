package com.wsw.protocol.tcp;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import com.wsw.bean.RPCRequest;
import com.wsw.protocol.Client;
import com.wsw.service.cache.URLCache;
import com.wsw.service.common.IOType;
import com.wsw.service.common.RPCConstants;

public class TCPClient implements Client {

	public Object doSend(RPCRequest rpcRequest) {
		Object res = null;
		try {
			String ioType = System.getProperty("ioType");
			if(IOType.BIO.ioName.equals(ioType)) {
				res = bioRun(rpcRequest);
			}else if(IOType.NIO.ioName.equals(ioType)) {
				res = nioRun(rpcRequest);
			}
		} catch (Exception e) {
		}
		return res;
	}

	private Object nioRun(RPCRequest rpcRequest){
		try {
			InetSocketAddress inetSocketAddress = new InetSocketAddress(rpcRequest.getUrl().getHost(), rpcRequest.getUrl().getProt());

			Selector selector = Selector.open();

			SocketChannel socketChannel = SocketChannel.open(inetSocketAddress);
			socketChannel.configureBlocking(false);
			socketChannel.register(selector, SelectionKey.OP_READ);
			//rpcRequest对象转byte数组
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(rpcRequest);
			byte[] bytes = bo.toByteArray();
			//向服务器发送信息
			socketChannel.write(ByteBuffer.wrap(bytes));
			//接受服务器响应
			ByteBuffer buffer = ByteBuffer.allocate(RPCConstants.NIO_BUFFER_SIZE);
			socketChannel.read(buffer);

			socketChannel.close();
			return new String(buffer.array());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	private Object bioRun(RPCRequest rpcRequest){
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

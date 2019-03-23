package com.wsw.protocol.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wsw.bean.User;
import com.wsw.service.cache.URLCache;
import com.wsw.service.common.RPCConstants;

public class TCPServiceHandler {

	public void handler() {
		ExecutorService pool = Executors.newFixedThreadPool(RPCConstants.CORE_POOL_SIZE);

		try {
			ServerSocket serverSocket = new ServerSocket(URLCache.getDefUrl().getProt());
			System.out.println("服务器启动，等待客户端的连接。。。");
			while (true) {
				Socket socket = serverSocket.accept();
//				Thread serverHandleThread = new Thread(new ServerHandleThread(socket));
				ServerHandleThread serverHandleThread = new ServerHandleThread(socket);
				Future<Object> submit = pool.submit(serverHandleThread);
				System.out.println(submit.get());

//				InetAddress inetAddress = socket.getInetAddress();
//				System.out.println("当前客户端的IP地址是：" + inetAddress.getHostAddress());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private class ServerHandleThread implements Callable<Object> {
		Socket socket;

		public ServerHandleThread(Socket socket) {
			super();
			this.socket = socket;
		}

		public Object call() throws Exception {
			OutputStream os = null;
			PrintWriter pw = null;
			try {
				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				// readObject()方法必须保证服务端和客户端的User包名一致，要不然会出现找不到类的错误
				System.out.println("客户端发送的对象：" + (User) ois.readObject());
				socket.shutdownInput();// 禁用套接字的输入流
				os = socket.getOutputStream();
				pw = new PrintWriter(os);
				pw.println("欢迎登录！");
				pw.flush();
				socket.shutdownOutput();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (pw != null) {
						pw.close();
					}
					if (os != null) {
						os.close();
					}
					if (socket != null) {
						socket.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			return null;
		}

	}

	public static void main(String[] args) {
		new TCPServiceHandler().handler();

	}

}

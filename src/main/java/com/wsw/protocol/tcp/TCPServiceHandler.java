package com.wsw.protocol.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.wsw.bean.RPCRequest;
import com.wsw.bean.URL;
import com.wsw.bean.User;
import com.wsw.exception.RPCException;
import com.wsw.protocol.InvokeMethodHandler;
import com.wsw.protocol.Server;
import com.wsw.provider.impl.HelloImpl;
import com.wsw.register.Register;
import com.wsw.service.cache.URLCache;
import com.wsw.service.common.RPCConstants;

public class TCPServiceHandler implements Server {

	private static Logger logger = Logger.getLogger(TCPServiceHandler.class);

	public void handle() {
		ExecutorService pool = Executors.newFixedThreadPool(RPCConstants.CORE_POOL_SIZE);

		try {
			ServerSocket serverSocket = new ServerSocket(URLCache.getDefUrl().getProt());
			System.out.println("服务器启动，等待客户端的连接。。。");
			while (true) {
				Socket socket = serverSocket.accept();
				Future<Object> submit = null;
				String name = UUID.randomUUID().toString();
				try {
					submit = pool.submit(new ServerHandleThread(socket, name));
				} catch (Exception e) {
					logger.error(name + "线程执行失败", e);
				}

//				if(submit != null) {
//					System.out.println(submit.get());
//				}

//				InetAddress inetAddress = socket.getInetAddress();
//				System.out.println("当前客户端的IP地址是：" + inetAddress.getHostAddress());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private class ServerHandleThread extends InvokeMethodHandler implements Callable<Object> {
		private Socket socket;

		public ServerHandleThread(Socket socket, String name) {
			super();
			this.socket = socket;
			Thread.currentThread().setName(name);
		}

		public Object call() throws Exception {
			OutputStream os = null;
			PrintWriter pw = null;
			try {
				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				Object readObject = ois.readObject();
				if (!(readObject instanceof RPCRequest)) {
					throw new RPCException("参数异常");
				}
				RPCRequest rpcRequest = (RPCRequest) readObject;

				Object invoke = super.doInvoke(rpcRequest);

				PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
				printWriter.println(invoke.toString());
				printWriter.flush();

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
		Register.regist("Hello", HelloImpl.class);
		new TCPServiceHandler().handle();

	}

}
package com.wsw.protocol.tcp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
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
import com.wsw.service.common.IOType;
import com.wsw.service.common.RPCConstants;
import com.wsw.util.CommonUtil;

public class TCPServiceHandler implements Server {

	private static Logger logger = Logger.getLogger(TCPServiceHandler.class);

	public void handle(URL url) {
		try {
			String ioType = System.getProperty("ioType");
			if(IOType.BIO.ioName.equals(ioType)) {
				bioRun(url);
			}else if(IOType.NIO.ioName.equals(ioType)) {
				nioRun(url);
			}
		} catch (Exception e) {
			logger.error("TCP服务器异常",e);
		}

	}
	
	private void nioRun(URL url) {
		//nio的三大核心： channel多路复用、selector选择器、buffer缓存
		try {
			Charset charset = Charset.forName("utf-8");
			InetSocketAddress inetSocketAddress = new InetSocketAddress(url.getProt());
			Selector selector = Selector.open();

			ServerSocketChannel channel = ServerSocketChannel.open();
			channel.configureBlocking(false);
			channel.bind(inetSocketAddress);
			channel.register(selector, SelectionKey.OP_ACCEPT);

			ByteBuffer buf = ByteBuffer.allocate(RPCConstants.NIO_BUFFER_SIZE);
			int j = 0 ;
			while (true) {
				System.out.println(j++);
				if(selector.select() == 0) {
					continue;
				}
				//如果select中有channel
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectedKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					//如果可以被访问
					if(selectionKey.isAcceptable()) {
						ServerSocketChannel c = (ServerSocketChannel) selectionKey.channel();
						SocketChannel clientChannel = c.accept();
						clientChannel.configureBlocking(false);
						clientChannel.register(selector, SelectionKey.OP_READ);
					}
					if(selectionKey.isReadable()) {
						//接受数据
						SocketChannel c = (SocketChannel) selectionKey.channel();
						c.read(buf);
//						ObjectOutputStream objectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
//						objectOutputStream.writeObject();
						ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(buf.array()));
						RPCRequest rpcRequest = (RPCRequest) objectInputStream.readObject();
						String interfaceName = rpcRequest.getInterfaceName();
						Class<?> impl = Register.getClass(url, interfaceName);
						
						Method method = impl.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
						Object invoke = method.invoke(impl.newInstance(), rpcRequest.getParams());
						c.write(ByteBuffer.wrap(invoke.toString().getBytes()));
						buf.clear();
					}
					iterator.remove();
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void bioRun(URL url) {
		ExecutorService pool = Executors.newFixedThreadPool(RPCConstants.CORE_POOL_SIZE);
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(url.getProt());
			System.out.println("TCP服务器启动成功");
			while (true) {
				Socket socket = serverSocket.accept();
//				Future<Object> submit = null;
				String name = CommonUtil.getUUID();
				try {
					pool.submit(new ServerHandleThread(socket, name));
				} catch (Exception e) {
					logger.error(name + "线程执行失败", e);
				}
			}
		} catch (Exception e) {
			logger.error("TCP服务",e);
		}finally {
			if(serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
				}
			}
			pool.shutdown();
			
		}
	}
	

	private class ServerHandleThread extends InvokeMethodHandler implements Callable<Object> {
		private Socket socket;
		private String name;

		public ServerHandleThread(Socket socket, String name) {
			super();
			this.socket = socket;
			this.name = name;
			Thread.currentThread().setName(name);
		}

		public Object call() throws Exception {
			OutputStream os = null;
			PrintWriter printWriter = null;
			try {
				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				Object readObject = ois.readObject();
				if (!(readObject instanceof RPCRequest)) {
					throw new RPCException("参数异常");
				}
				RPCRequest rpcRequest = (RPCRequest) readObject;

				Object invoke = super.doInvoke(rpcRequest);

				printWriter = new PrintWriter(socket.getOutputStream(), true);
				printWriter.println(invoke.toString());
				printWriter.flush();

			} catch (Exception e) {
				logger.error(name+"执行失败");
			} finally {
				try {
					if (printWriter != null) {
						printWriter.close();
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

}

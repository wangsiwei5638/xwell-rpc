package com.wsw.protocol.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.attribute.UserPrincipalNotFoundException;

import com.wsw.bean.URL;
import com.wsw.bean.User;
import com.wsw.service.cache.URLCache;

public class TCPClient {

	
	public void doSend() {
		 // 1.创建客户端的Socket，指定服务器的IP和端口
        try {
            Socket socket = newSocket();
             
            // 2.获取该Socket的输出流，用来向服务器发送信息
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(new User(1, "root", "123456"));
            socket.shutdownOutput();
            String infoString=null;
            
            // 3.获取输入流，取得服务器的信息
            InputStream is = socket.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String info=null;
            while((info=br.readLine())!=null){
                System.out.println("服务器端的信息：" + info);
            }
            socket.shutdownInput();
            oos.close();
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
	}
	
	private static Socket newSocket() throws UnknownHostException, IOException {
		return new Socket(URLCache.getDefUrl().getHost(), URLCache.getDefUrl().getProt());

	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			new TCPClient().doSend();
		}
	}
	
}

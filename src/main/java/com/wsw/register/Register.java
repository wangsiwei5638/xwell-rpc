package com.wsw.register;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.wsw.bean.URL;
import com.wsw.service.cache.URLCache;

/**
 * @author wsw
 *
 */
public class Register {

	private static Map<String, Map<URL,Class<?>>> register = new ConcurrentHashMap<String, Map<URL,Class<?>>>();
	static final Lock LOCK = new ReentrantLock(true);
	static {
		//注册缺省配置
		
	}
	
	private static void init() {
		//遍历项目，
		
		
	}
	
	/**
	  		注册服务
	 */
	public static void regist(URL url,String interfaceName,Class<?> impl) {
		try {
//			LOCK.lock();
			if(url == null) {
				//注册失败
				return ;
			}
			Map<URL,Class<?>> registServer = new HashMap<URL,Class<?>>();
			registServer.put(url, impl);
			register.put(interfaceName, registServer);
		} catch (Exception e) {
			System.out.println("服务注册失败");
			e.printStackTrace();
		} finally {
//			LOCK.unlock();
			
		}
		
	}
	
	public static void regist(String interfaceName,Class<?> impl) {
		
		regist(URLCache.getDefUrl(), interfaceName, impl);
	}
	
//	public static URL getRegister(String interfaceName) {
//		return register.get(interfaceName).keySet().iterator().next();
//	}
	
	public static Class<?> getClass(URL url,String interfaceName) {
		return register.get(interfaceName).get(url);
	}
	
}

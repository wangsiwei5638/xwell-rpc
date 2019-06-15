package com.wsw.util;

import java.util.Date;
import java.util.Random;

/**   
 * @ClassName:  LogUtil   
 * @Description:
 * 
 * 					日志工具类
 * 
 * @author: wsw
 * @date:   2019年6月15日
 * @Copyright: http://www.iwangsiwei.com
 */
public class LogUtil {

	final private static ThreadLocal<String> LOG_THREADLOCAL = new InheritableThreadLocal<>();
	
	public String getLogKey() {
		if(LOG_THREADLOCAL.get()==null) {
			LOG_THREADLOCAL.set(System.currentTimeMillis()+""+new Random().nextInt(99999));
		}
		return LOG_THREADLOCAL.get();
	}
	
	
	
	
	public static void main(String[] args) {
		
		System.out.println(new Date(System.currentTimeMillis()+1000000));
	}
	
}

package com.wsw.util;

import com.wsw.exception.RPCException;

/**   
 * @ClassName:  AssertUtil   
 * @Description:
 * 					断言工具类   
 * @author: wsw
 * @date:   2019年3月17日
 * @Copyright: http://www.iwangsiwei.com
 */
public class AssertUtil {

	public static void isNotNull(Object object, String message) {
		if (object == null) {
			throw new RPCException(message);
		}
	}
	
	public static void isNotNull(Object object) {
		isNotNull(object, "对象为空");
	}
	
}

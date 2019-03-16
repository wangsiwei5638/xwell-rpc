package com.wsw.util;

import com.wsw.exception.RPCException;

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

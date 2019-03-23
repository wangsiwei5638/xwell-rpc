package com.wsw.protocol;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.wsw.bean.RPCRequest;
import com.wsw.bean.URL;
import com.wsw.register.Register;

public class InvokeMethodHandler {

	/**
	 * @param rpcRequest
	 * @return
	 * 			通过反射去调用rpcRequest中的目标方法
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	protected Object doInvoke(RPCRequest rpcRequest) throws NoSuchMethodException, SecurityException,
		IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		String interfaceName = rpcRequest.getInterfaceName();
		URL url = rpcRequest.getUrl();
		Class<?> impl = Register.getClass(url, interfaceName);
		
		Method method = impl.getMethod(rpcRequest.getMethodName(), rpcRequest.getTypes());
		Object resObject = method.invoke(impl.newInstance(), rpcRequest.getParams());
		
		return resObject;
	}
}

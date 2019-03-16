package com.wsw.exception;

/**   
 * @ClassName:  RPCException   
 * @Description:
 * 					RPC异常
 * @author: wsw
 * @date:   2019年3月16日
 * @Copyright: http://www.iwangsiwei.com
 */
public class RPCException extends RuntimeException {

	private static final long serialVersionUID = 8856043010814743405L;

	public RPCException() {
		super();
	}

	public RPCException(String message) {
		super(message);
	}

	public RPCException(Throwable cause) {
		super(cause);
	}

	
}

package com.wsw.protocol;

import com.wsw.bean.RPCRequest;

/**   
 * @ClassName:  Client   
 * @Description:
 * 
 * 					客户端
 * 
 * @author: wsw
 * @date:   2019年3月23日
 * @Copyright: http://www.iwangsiwei.com
 */
public interface Client {

	Object doSend(RPCRequest rpcRequest);
}

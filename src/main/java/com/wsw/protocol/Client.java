package com.wsw.protocol;

import com.wsw.bean.RPCRequest;

public interface Client {

	Object doSend(RPCRequest rpcRequest);
}
